package kuit.springbasic.core.mvc.controller.v1;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.mvc.model.ModelAndView;

import java.sql.SQLException;
import java.util.Map;

public interface ControllerV1 {

    default void setIsLoggedIn(boolean isLoggedIn) {
    }

    default void setSession(HttpSession session) {
    }

    ModelAndView execute(Map<String, String> params) throws SQLException;

}
