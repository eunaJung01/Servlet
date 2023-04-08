package kuit.springbasic.web.controller.v1.qna.question;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class AddQuestionControllerV1 implements ControllerV1 {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws SQLException {
        log.info("AddQuestionControllerV1");

        Question question = new Question(
                params.get("writer"),
                params.get("title"),
                params.get("contents"),
                0
        );
        questionDao.insert(question);

        return new ModelAndView("redirect:/v1");
    }

}
