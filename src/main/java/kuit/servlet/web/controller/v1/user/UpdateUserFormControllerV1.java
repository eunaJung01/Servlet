package kuit.servlet.web.controller.v1.user;

import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import kuit.servlet.web.dao.UserDao;
import kuit.servlet.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class UpdateUserFormControllerV1 implements ControllerV1 {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("UpdateUserFormControllerV1");

        String userId = params.get("userId");
        User user = userDao.findByUserId(userId);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("/v1/user/updateForm");
            modelAndView.getModel().put("user", user);
            return modelAndView;
        }
        return new ModelAndView("redirect:/v1");
    }

}
