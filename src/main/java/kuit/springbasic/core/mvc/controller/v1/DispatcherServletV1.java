package kuit.springbasic.core.mvc.controller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.core.mvc.view.View;
import kuit.springbasic.core.mvc.util.UserSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "dispatcher", urlPatterns = "/v1/*", loadOnStartup = 1)
public class DispatcherServletV1 extends HttpServlet {

    private RequestMappingV1 requestMapping;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() {
        requestMapping = new RequestMappingV1();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("DispatcherServlet");

        ControllerV1 controller = getController(request, response);
        if (controller == null) return;

        ModelAndView modelAndView = getModelAndView(request, controller);
        String viewName = modelAndView.getViewName();
        if (viewName == null) return;

        boolean isRedirect = viewName.startsWith(REDIRECT_PREFIX);
        View view = viewResolver(isRedirect, viewName);
        view.render(isRedirect, modelAndView.getModel(), request, response);
    }

    private ControllerV1 getController(HttpServletRequest request, HttpServletResponse response) {
        ControllerV1 controller = requestMapping.getController(request);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        HttpSession session = request.getSession();
        controller.setSession(session);

        boolean isLoggedIn = UserSessionUtils.isLoggedIn(session);
        controller.setIsLoggedIn(isLoggedIn);
        log.info("isLoggedIn={}", isLoggedIn);

        return controller;
    }

    private ModelAndView getModelAndView(HttpServletRequest request, ControllerV1 controller) {
        Map<String, String> params = createParamMap(request);
        ModelAndView modelAndView;
        try {
            modelAndView = controller.execute(params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return modelAndView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> params.put(paramName, request.getParameter(paramName)));
        return params;
    }

    private View viewResolver(boolean isRedirect, String viewName) {
        if (isRedirect) {
            return new View(viewName.substring(REDIRECT_PREFIX.length()));
        }
        return new View("/WEB-INF/" + viewName + ".jsp");
    }

}
