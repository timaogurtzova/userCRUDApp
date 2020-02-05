package servlet;

import model.User;
import service.ServiceUser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "getServlet",
        urlPatterns = {"/get"}
)
public class ServletApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> usersList = ServiceUser.getInstance().getAllUserService();
        req.setAttribute("users", usersList);
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
