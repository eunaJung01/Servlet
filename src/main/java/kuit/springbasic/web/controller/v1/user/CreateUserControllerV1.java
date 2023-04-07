package kuit.springbasic.web.controller.v1.user;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.domain.User;
import kuit.springbasic.web.dao.UserDao;

import java.util.Map;

public class CreateUserControllerV1 implements ControllerV1 {

    private UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) {
        User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
        userDao.insert(user);

        ModelAndView modelAndView = new ModelAndView("/user/list");
        return modelAndView;
    }

}
