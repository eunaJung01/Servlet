package kuit.springbasic.web.controller.v3.user;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserListControllerV2 implements ControllerV2 {

    private boolean isLoggedIn = false;

    private final UserDao userDao = new UserDao();

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) {
        log.info("ListUserControllerV2");

        if (isLoggedIn) {
            model.put("users", userDao.findAll());
            return "/v3/v2/user/list";
        }
        return "redirect:/v3/v2/user/loginForm";
    }

}
