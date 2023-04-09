package kuit.servlet.web.controller.v3.qna.question;

import kuit.servlet.core.mvc.controller.v2.ControllerV2;
import kuit.servlet.web.dao.QuestionDao;
import kuit.servlet.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class AddQuestionControllerV2 implements ControllerV2 {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        log.info("AddQuestionControllerV2");

        Question question = new Question(
                params.get("writer"),
                params.get("title"),
                params.get("contents"),
                0
        );
        questionDao.insert(question);

        return "redirect:/v3/v2";
    }

}
