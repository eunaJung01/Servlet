package kuit.springbasic.core.mvc.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import kuit.springbasic.web.controller.v1.HomeControllerV1;
import kuit.springbasic.web.controller.v1.user.CreateUserControllerV1;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RequestMapping {

    private final Map<String, ControllerV1> controllers = new HashMap<>();

    public RequestMapping() {
        initControllers();
    }

    private void initControllers() {
        controllers.put("/v1", new HomeControllerV1());

        controllers.put("/v1/user/signup", new CreateUserControllerV1());
    }

    public ControllerV1 getController(HttpServletRequest request) {
        log.info("requestURI={}", request.getRequestURI());
        return controllers.get(request.getRequestURI());
    }

}
