package kuit.servlet.core.mvc.controller.v2;

import jakarta.servlet.http.HttpSession;
import kuit.servlet.web.domain.User;

import java.sql.SQLException;
import java.util.Map;

public interface ControllerV2 {

    default void setIsLoggedIn(boolean isLoggedIn) {
    }

    default void setSession(HttpSession session) {
    }

    default void setUserFromSession(User user) {
    }

    String execute(Map<String, String> params, Map<String, Object> model) throws SQLException;

}
