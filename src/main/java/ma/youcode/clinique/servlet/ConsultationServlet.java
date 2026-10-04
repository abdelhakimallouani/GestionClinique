package ma.youcode.clinique.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.youcode.clinique.dao.JdbcConsultationDAO;
import ma.youcode.clinique.dao.JdbcPatientDAO;
import ma.youcode.clinique.entity.Consultation;
import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.service.ConsultationService;
import ma.youcode.clinique.service.PatientService;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/medecin/patients",
        "/medecin/consultation",
        "/medecin/consultation/edit"
})
public class ConsultationServlet extends HttpServlet {

    private ConsultationService consultationService;
    private PatientService patientService;

    @Override
    public void init() {

        consultationService = new ConsultationService(
                new JdbcConsultationDAO());

        patientService = new PatientService(
                new JdbcPatientDAO());
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/medecin/patients".equals(path)) {

            List<Consultation> consultations = consultationService.lister();

            request.setAttribute("consultations", consultations);

            request.getRequestDispatcher(
                    "/medecin/patients.jsp").forward(request, response);

        } else if ("/medecin/consultation".equals(path)) {

            Long consultationId = Long.parseLong(
                    request.getParameter("id"));

            Consultation consultation = consultationService
                    .findById(consultationId)
                    .orElseThrow();

            Patient patient = patientService
                    .findById(
                            consultation.getPatientId())
                    .orElseThrow();

            request.setAttribute(
                    "consultation",
                    consultation);

            request.setAttribute(
                    "patient",
                    patient);

            request.getRequestDispatcher(
                    "/medecin/consultation.jsp").forward(request, response);

        } else if ("/medecin/consultation/edit".equals(path)) {

            Long consultationId = Long.parseLong(
                    request.getParameter("id"));

            Consultation consultation = consultationService
                    .findById(consultationId)
                    .orElseThrow();

            Patient patient = patientService
                    .findById(
                            consultation.getPatientId())
                    .orElseThrow();

            request.setAttribute(
                    "consultation",
                    consultation);

            request.setAttribute(
                    "patient",
                    patient);

            request.getRequestDispatcher(
                    "/medecin/consultation-edit.jsp").forward(request, response);
        }
    }
}