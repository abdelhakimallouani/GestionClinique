package ma.youcode.clinique.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import ma.youcode.clinique.service.ConsultationService;
import ma.youcode.clinique.dao.JdbcConsultationDAO;
import ma.youcode.clinique.entity.Consultation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/medecin/patients")
public class ConsultationServlet extends HttpServlet {
    private ConsultationService consultationService;

    @Override
    public void init() {
        consultationService = new ConsultationService(new JdbcConsultationDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Consultation> consultations = consultationService.lister();
        request.setAttribute("consultations", consultations);
        request.getRequestDispatcher("/medecin/patients.jsp").forward(request, response);
    }

}
