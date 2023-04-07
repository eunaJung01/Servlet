package kuit.springbasic.core.mvc.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class View {

    private String viewPath;

    public View(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(boolean isRedirect, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (isRedirect) {
            response.sendRedirect(viewPath);
            return;
        }
        setModelToRequestAttribute(model, request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }

    private void setModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach(request::setAttribute);
    }

}
