package kuit.servlet.web.controller.v1.qna.question;

import kuit.servlet.core.mvc.controller.v1.ControllerV1;
import kuit.servlet.core.mvc.model.ModelAndView;
import kuit.servlet.web.dao.QuestionDao;
import kuit.servlet.web.domain.Question;
import kuit.servlet.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UpdateQuestionControllerV1 implements ControllerV1 {

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
        log.info("UpdateQuestionControllerV1");

        if (!isLoggedIn) {
            return new ModelAndView("redirect:/v1/user/loginForm");
        }

        int questionId = Integer.parseInt(params.get("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(Objects.requireNonNull(userFromSession))) {
            throw new IllegalArgumentException();
        }

        String title = params.get("title");
        String contents = params.get("contents");
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);

        return new ModelAndView("redirect:/v1");
    }

}
