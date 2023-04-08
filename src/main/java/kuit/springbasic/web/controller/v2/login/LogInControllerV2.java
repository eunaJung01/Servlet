package kuit.springbasic.web.controller.v2.login;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class LogInControllerV2 implements ControllerV2 {

    private HttpSession session;

    private final UserDao userDao = new UserDao();

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("LogInControllerV2");

        String userId = params.get("userId");
        String password = params.get("password");

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            session.setAttribute("user", user);
            return "redirect:/v2";
        }
        return "redirect:/v2/user/loginFailed";
    }

}
