package kuit.servlet.web.controller.v1.login;

import jakarta.servlet.http.HttpSession;
import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import kuit.servlet.web.domain.User;
import kuit.servlet.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class LogInControllerV1 implements ControllerV1 {

    private HttpSession session;

    private final UserDao userDao = new UserDao();

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("LogInControllerV1_V3");

        String userId = params.get("userId");
        String password = params.get("password");

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/v1");
        }
        return new ModelAndView("redirect:/v1/user/loginFailed");
    }

}
