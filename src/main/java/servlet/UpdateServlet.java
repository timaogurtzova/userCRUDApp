package servlet;

import model.User;
import service.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "updateServlet",
        urlPatterns = {"/update"}
)
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String password = req.getParameter("password");
        String city = req.getParameter("city");

        String oldName = req.getParameter("oldname");
        String oldPass = req.getParameter("oldpassword");
        try {
            Long idLong = Long.parseLong(id);
            int ageInt = Integer.parseInt(age);
            boolean validateOrNot = ServiceUser.getInstance().validateUserService(idLong, oldName, oldPass);
            if (validateOrNot){
                User user = new User(idLong, name, ageInt, password, city);
                ServiceUser.getInstance().updateUserByIdService(idLong, user );
            }
        }catch (NumberFormatException e){

        }
    }
}
