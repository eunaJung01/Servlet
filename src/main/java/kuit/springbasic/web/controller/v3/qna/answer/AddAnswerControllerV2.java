package kuit.springbasic.web.controller.v3.qna.answer;

import kuit.springbasic.core.mvc.controller.v2.ControllerV2;
import kuit.springbasic.web.dao.AnswerDao;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Answer;
import kuit.springbasic.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class AddAnswerControllerV2 implements ControllerV2 {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("AddAnswerControllerV2");

        int questionId = Integer.parseInt(params.get("questionId"));
        String writer = params.get("writer");
        String contents = params.get("contents");

        Answer answer = new Answer(questionId, writer, contents);
        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        model.put("answer", savedAnswer);
        return "jsonView";
    }

}
