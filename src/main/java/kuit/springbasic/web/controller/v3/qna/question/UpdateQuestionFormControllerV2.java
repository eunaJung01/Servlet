package kuit.springbasic.web.controller.v3.qna.question;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import kuit.springbasic.web.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class UpdateQuestionFormControllerV2 implements ControllerV2 {

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
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("UpdateQuestionFormControllerV2");

        if (!isLoggedIn) {
            return "redirect:/v3/v2/user/loginForm";
        }

        int questionId = Integer.parseInt(params.get("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(Objects.requireNonNull(userFromSession))) {
            throw new IllegalArgumentException();
        }

        model.put("question", question);
        return "/v3/v2/qna/updateForm";
    }

}
