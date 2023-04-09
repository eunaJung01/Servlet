package kuit.servlet.core.mvc.controller.v3.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class ControllerV1HandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV1);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SQLException {
        ControllerV1 controller = (ControllerV1) handler;
        setControllerFields(request, controller);

        Map<String, String> params = createParamMap(request);
        ModelAndView modelAndView = controller.execute(params);
        return modelAndView;
    }

}
