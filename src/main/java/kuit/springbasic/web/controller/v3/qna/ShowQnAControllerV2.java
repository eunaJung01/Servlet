package kuit.springbasic.web.controller.v3.qna;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.AnswerDao;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Answer;
import kuit.springbasic.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
public class ShowQnAControllerV2 implements ControllerV2 {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("ShowQnAControllerV2");

        int questionId = Integer.parseInt(params.get("questionId"));

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        model.put("question", question);
        model.put("answers", answers);
        return "/v3/v2/qna/show";
    }

}
