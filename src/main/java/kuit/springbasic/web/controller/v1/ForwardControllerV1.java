package kuit.springbasic.web.controller.v1;

import kuit.springbasic.core.mvc.controller.v1.ControllerV1;
import kuit.springbasic.core.mvc.model.ModelAndView;

import java.util.Map;

public class ForwardControllerV1 implements ControllerV1 {

    private String forwardUrl;

    public ForwardControllerV1(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
    }

    @Override
    public ModelAndView execute(Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView(forwardUrl);
        return modelAndView;
    }

}
