package kuit.springbasic.web.controller.v1;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;
import kuit.springbasic.web.dao.QuestionDao;
import kuit.springbasic.domain.Question;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class HomeControllerV1 implements ControllerV1 {

    private final QuestionDao questionDAO = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) {
        log.info("HomeControllerV1");

        List<Question> questions = questionDAO.findAll();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.getModel().put("questions", questions);

        log.info("modelAndView.getViewName={}", modelAndView.getViewName());
        return modelAndView;
    }

}