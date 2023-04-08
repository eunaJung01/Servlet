package kuit.springbasic.web.controller.v1.user;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.domain.User;
import kuit.springbasic.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CreateUserControllerV1 implements ControllerV1 {

    private UserDao userDao = new UserDao();

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
