package kuit.springbasic.web.controller.v1.qna.question;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UpdateQuestionFormControllerV1 implements ControllerV1 {

    private boolean isLoggedIn;
    private User userFromSession;

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public void setUserFromSession(User user) {
        this.userFromSession = user;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("UpdateQuestionFormControllerV1");

        if (!isLoggedIn) {
            return new ModelAndView("redirect:/v1/user/loginForm");
        }

        int questionId = Integer.parseInt(params.get("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(Objects.requireNonNull(userFromSession))) {
            throw new IllegalArgumentException();
        }

        ModelAndView modelAndView = new ModelAndView("/v1/qna/updateForm");
        modelAndView.getModel().put("question", question);
        return modelAndView;
    }

}
