package kuit.springbasic.web.controller.v3.user;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class CreateUserControllerV2 implements ControllerV2 {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("CreateUserControllerV2");

        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.insert(user);

        return "redirect:/v3/v2/user/list";
    }

}
