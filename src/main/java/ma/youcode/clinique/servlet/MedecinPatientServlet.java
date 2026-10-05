package ma.youcode.clinique.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.clinique.dao.JdbcPatientDAO;
import ma.youcode.clinique.dao.JdbcConsultationDAO;
import ma.youcode.clinique.service.PatientService;

@WebServlet("/medecin/patients")
public class MedecinPatientServlet extends HttpServlet {
    private final PatientService patientService = new PatientService(new JdbcPatientDAO(), new JdbcConsultationDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateParam = request.getParameter("date");
        LocalDate date = LocalDate.now();

        if (dateParam != null && !dateParam.isBlank()) {
            try {
                date = LocalDate.parse(dateParam);
            } catch (DateTimeParseException e) {
                request.setAttribute("erreurDate", "Date invalide. Choisissez une date valide.");
            }
        }

        String tri = request.getParameter("tri");
        if (!"nom".equals(tri) && !"naissance".equals(tri)) {
            tri = "arrivee";
        }
        String ordre = "desc".equals(request.getParameter("ordre")) ? "desc" : "asc";
        request.setAttribute("triSelectionne", tri);
        request.setAttribute("ordreSelectionne", ordre);
        request.setAttribute("dateSelectionnee", date);
        request.setAttribute("patients", patientService.listerPatientsParDate(date, tri, ordre));
        request.getRequestDispatcher("/medecin/patients.jsp").forward(request, response);
    }
}
