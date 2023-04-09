package kuit.servlet.web.controller.v3.qna;

import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class QnAFormControllerV1 implements ControllerV1 {

    private boolean isLoggedIn;

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("QnAFormControllerV1");

        if (isLoggedIn) {
            return new ModelAndView("/v3/v1/qna/form");
        }
        return new ModelAndView("redirect:/v3/v1/user/loginForm");
    }

}
