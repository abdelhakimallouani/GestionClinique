package ma.youcode.clinique.servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.service.PatientService;

@WebServlet("/infirmier/patients")
public class PatientServlet extends HttpServlet {
    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("patients", patientService.listerPatientsDuJour());
        request.getRequestDispatcher("/infirmier/patients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Patient patient = new Patient(
                    request.getParameter("nom"),
                    request.getParameter("prenom"),
                    LocalDate.parse(request.getParameter("date_naissance")),
                    request.getParameter("numero_securite_sociale"),
                    request.getParameter("tension_arterielle"),
                    Integer.parseInt(request.getParameter("frequence_cardiaque")),
                    Double.parseDouble(request.getParameter("temperature")),
                    Integer.parseInt(request.getParameter("frequence_respiratoire")));

            patientService.enregistrer(patient);
            response.sendRedirect(request.getContextPath() + "/infirmier/patients");
        } catch (RuntimeException e) {
            request.setAttribute("erreur", "Vérifie les informations saisies.");
            request.getRequestDispatcher("/infirmier/createpatien.jsp").forward(request, response);
        }
    }
}
