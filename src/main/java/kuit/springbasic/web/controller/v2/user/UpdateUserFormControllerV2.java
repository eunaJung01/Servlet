package kuit.springbasic.web.controller.v2.user;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class UpdateUserFormControllerV2 implements ControllerV2 {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("UpdateUserFormControllerV2");

        String userId = params.get("userId");
        User user = userDao.findByUserId(userId);
        if (user != null) {
            model.put("user", user);
            return "/v2/user/updateForm";
        }
        return "redirect:/v2";
    }

}
