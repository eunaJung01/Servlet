package kuit.springbasic.core.mvc.controller.v1;

import kuit.springbasic.core.mvc.model.ModelAndView;

import java.util.Map;

public interface ControllerV1 {

    ModelAndView execute(Map<String, String> params);

}
