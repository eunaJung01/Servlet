package kuit.springbasic.web.controller.v1.qna;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.AnswerDao;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Answer;
import kuit.springbasic.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
public class ShowQnAControllerV1 implements ControllerV1 {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("ShowQnAController");

        int questionId = Integer.parseInt(params.get("questionId"));

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        ModelAndView modelAndView = new ModelAndView("/v1/qna/show");
        modelAndView.getModel().put("question", question);
        modelAndView.getModel().put("answers", answers);
        return modelAndView;
    }

}
