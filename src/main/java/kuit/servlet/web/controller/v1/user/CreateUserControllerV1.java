package kuit.servlet.web.controller.v1.user;

import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import kuit.servlet.web.domain.User;
import kuit.servlet.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CreateUserControllerV1 implements ControllerV1 {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) {
        log.info("CreateUserControllerV1");

        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.insert(user);

        return new ModelAndView("redirect:/v1/user/list");
    }

}
