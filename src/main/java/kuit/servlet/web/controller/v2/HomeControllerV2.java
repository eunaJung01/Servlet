package kuit.servlet.web.controller.v2;

import kuit.servlet.core.mvc.controller.v2.ControllerV2;
import kuit.servlet.web.dao.QuestionDao;
import kuit.servlet.web.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class HomeControllerV2 implements ControllerV2 {

    private final QuestionDao questionDAO = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) {
        log.info("HomeControllerV2");

        List<Question> questions = questionDAO.findAll();
        model.put("questions", questions);
        return "/v2/homeV2";
    }

}
