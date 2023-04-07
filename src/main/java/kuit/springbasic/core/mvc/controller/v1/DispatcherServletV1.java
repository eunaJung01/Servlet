package kuit.springbasic.core.mvc.controller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.core.mvc.view.View;
import kuit.springbasic.web.util.UserSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "dispatcher", urlPatterns = "/v1/*", loadOnStartup = 1)
public class DispatcherServletV1 extends HttpServlet {

    private RequestMapping requestMapping;

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("DispatcherServlet");

        ControllerV1 controller = getController(request, response);
        if (controller == null) return;

        Map<String, String> params = createParamMap(request);
        ModelAndView modelAndView = controller.execute(params);
        String viewName = modelAndView.getViewName(); // 논리 이름

        View view = viewResolver(viewName); // 물리 이름 생성 후 View 객체 반환
        view.render(modelAndView.getModel(), request, response);
    }

    private ControllerV1 getController(HttpServletRequest request, HttpServletResponse response) {
        ControllerV1 controller = requestMapping.getController(request);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        boolean isLoggedIn = UserSessionUtils.isLoggedIn(request.getSession());
        log.info("isLoggedIn={}", isLoggedIn);
        controller.setIsLoggedIn(isLoggedIn);
        return controller;
    }

    /**
     * @param request
     * @return HttpServletRequest 파라미터들을 저장한 Map
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> params.put(paramName, request.getParameter(paramName)));
        return params;
    }

    private View viewResolver(String viewName) {
        return new View("/WEB-INF/" + viewName + ".jsp");
    }

}
