package kuit.servlet.web.controller.v2.qna;

import kuit.servlet.core.mvc.controller.v2.ControllerV2;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class QnAFormControllerV2 implements ControllerV2 {

    private boolean isLoggedIn;

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) {
        log.info("QnAFormControllerV2");

        if (isLoggedIn) {
            return "/v2/qna/form";
        }
        return "redirect:/v2/user/loginForm";
    }

}
