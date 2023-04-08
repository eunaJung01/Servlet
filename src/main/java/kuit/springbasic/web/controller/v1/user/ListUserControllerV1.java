package kuit.springbasic.web.controller.v1.user;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ListUserControllerV1 implements ControllerV1 {

    private boolean isLoggedIn = false;

    private UserDao userDao = new UserDao();

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) {
        log.info("ListUserControllerV1");

        if (isLoggedIn) {
            ModelAndView modelAndView = new ModelAndView("/v1/user/list");
            modelAndView.getModel().put("users", userDao.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/v1/user/loginForm");
    }

}
