package kuit.servlet.core.mvc.util;

import jakarta.servlet.http.HttpSession;
import kuit.servlet.web.domain.User;

import static kuit.servlet.config.Constant.USER_SESSION_KEY;

public class UserSessionUtils {

    public static User getUserFromSession(HttpSession session) {
        Object user = session.getAttribute(USER_SESSION_KEY);
        if (user == null) {
            return null;
        }
        return (User) user;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getUserFromSession(session) != null;
    }

}
