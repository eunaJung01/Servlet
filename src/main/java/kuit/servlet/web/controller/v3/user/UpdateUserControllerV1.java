package kuit.servlet.web.controller.v3.user;

import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import kuit.servlet.web.dao.UserDao;
import kuit.servlet.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class UpdateUserControllerV1 implements ControllerV1 {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("UpdateUserControllerV1");

        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.update(user);

        return new ModelAndView("redirect:/v3/v1/user/list");
    }

}
