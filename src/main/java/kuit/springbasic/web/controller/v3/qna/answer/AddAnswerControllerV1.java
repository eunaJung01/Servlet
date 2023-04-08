package kuit.springbasic.web.controller.v3.qna.answer;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.AnswerDao;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Answer;
import kuit.springbasic.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class AddAnswerControllerV1 implements ControllerV1 {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("AddAnswerControllerV1");

        int questionId = Integer.parseInt(params.get("questionId"));
        String writer = params.get("writer");
        String contents = params.get("contents");

        Answer answer = new Answer(questionId, writer, contents);
        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.getModel().put("answer", savedAnswer);
        return modelAndView;
    }

}
