package kuit.springbasic.web.controller.v1.login;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.domain.User;
import kuit.springbasic.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class LogInControllerV1 implements ControllerV1 {

    HttpSession session;

    private UserDao userDao = new UserDao();

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("LogInControllerV1");

        String userId = params.get("userId");
        String password = params.get("password");
        User loggedInUser = new User(userId, password);

        User user = userDao.findByUserId(userId);

        ModelAndView modelAndView;
        if (user != null && user.isSameUser(loggedInUser)) {
            session.setAttribute("user", user);
            modelAndView = new ModelAndView("redirect:/v1");
            return modelAndView;
        }
        return new ModelAndView("redirect:/v1/user/loginFailed");
    }

}
