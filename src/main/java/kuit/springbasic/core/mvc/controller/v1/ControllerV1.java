package kuit.springbasic.core.mvc.controller.v1;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.domain.User;

import java.sql.SQLException;
import java.util.Map;

public interface ControllerV1 {

    default void setIsLoggedIn(boolean isLoggedIn) {
    }

    default void setSession(HttpSession session) {
    }

    default void setUserFromSession(User user) {
    }

    ModelAndView execute(Map<String, String> params) throws SQLException;

}
