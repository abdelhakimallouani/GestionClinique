package ma.youcode.clinique.servlet;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.clinique.dao.JdbcUserDAO;
import ma.youcode.clinique.dao.UserDAO;
import ma.youcode.clinique.entity.Utilisateur;
import ma.youcode.clinique.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() {

        UserDAO userDAO = new JdbcUserDAO();

        userService = new UserService(userDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<Utilisateur> optionalUser = userService.login(login, password);

        if (optionalUser.isEmpty()) {

            request.setAttribute("error", "Login ou mot de passe incorrect");

            request.getRequestDispatcher("/login.jsp").forward(request, response);

            return;
        }

        Utilisateur user = optionalUser.get();

        HttpSession session = request.getSession();

        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());

        if ("INFIRMIER".equals(user.getRole())) {

            response.sendRedirect(request.getContextPath() + "/infirmier/patients");

        } else if ("GENERALISTE".equals(user.getRole())) {

            response.sendRedirect(request.getContextPath() + "/medecin/patients");
        }
    }
}
