package kuit.springbasic.web.controller.v2.user;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class UpdateUserControllerV2 implements ControllerV2 {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("UpdateUserControllerV2");

        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.update(user);

        return "redirect:/v2/user/list";
    }

}
