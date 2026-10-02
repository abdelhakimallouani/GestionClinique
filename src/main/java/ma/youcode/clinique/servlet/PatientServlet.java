package ma.youcode.clinique.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.clinique.dao.JdbcPatientDAO;
import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.service.PatientService;

@WebServlet("/infirmier/patients")
public class PatientServlet extends HttpServlet {
    private final PatientService patientService = new PatientService(new JdbcPatientDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("nouveau".equals(request.getParameter("action"))) {
            request.getRequestDispatcher("/infirmier/createpatien.jsp").forward(request, response);
            return;
        }
        request.setAttribute("patients", patientService.listerPatientsDuJour());
        request.getRequestDispatcher("/infirmier/patients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<String> erreurs = new ArrayList<>();
        String nom = valeur(request, "nom");
        String prenom = valeur(request, "prenom");
        String dateSaisie = valeur(request, "date_naissance");
        String numeroSocial = valeur(request, "numero_securite_sociale");
        String tension = valeur(request, "tension_arterielle");

        if (!nom.matches("[\\p{L}\\p{M}][\\p{L}\\p{M} '-]{0,99}")) {
            erreurs.add("Le nom est obligatoire, doit contenir des lettres et faire 100 caractères maximum.");
        }
        if (!prenom.matches("[\\p{L}\\p{M}][\\p{L}\\p{M} '-]{0,99}")) {
            erreurs.add("Le prénom est obligatoire, doit contenir des lettres et faire 100 caractères maximum.");
        }
        try {
            LocalDate dateNaissance = LocalDate.parse(dateSaisie);
            if (dateNaissance.isAfter(LocalDate.now())) {
                erreurs.add("La date de naissance ne peut pas être dans le futur.");
            }
        } catch (DateTimeParseException e) {
            erreurs.add("La date de naissance est obligatoire et doit être valide.");
        }
        if (numeroSocial.isEmpty() || numeroSocial.length() > 50) {
            erreurs.add("Le numéro de sécurité sociale est obligatoire (50 caractères maximum).");
        }
        if (!tension.isEmpty()) {
            if (!tension.matches("[1-9][0-9]{0,2}/[1-9][0-9]{0,2}")) {
                erreurs.add("La tension doit être au format systolique/diastolique, par exemple 120/80.");
            } else {
                String[] valeursTension = tension.split("/");
                if (Integer.parseInt(valeursTension[0]) <= Integer.parseInt(valeursTension[1])) {
                    erreurs.add("La valeur systolique doit être supérieure à la valeur diastolique.");
                }
            }
        }
        verifierEntierPositif(valeur(request, "frequence_cardiaque"), "La fréquence cardiaque", erreurs);
        verifierTemperature(valeur(request, "temperature"), erreurs);
        verifierEntierPositif(valeur(request, "frequence_respiratoire"), "La fréquence respiratoire", erreurs);

        if (!erreurs.isEmpty()) {
            request.setAttribute("erreurs", erreurs);
            request.getRequestDispatcher("/infirmier/createpatien.jsp").forward(request, response);
            return;
        }

        try {
            Patient patient = new Patient(
                    nom,
                    prenom,
                    LocalDate.parse(request.getParameter("date_naissance")),
                    numeroSocial,
                    tension.isEmpty() ? null : tension,
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

    private String valeur(HttpServletRequest request, String nomParametre) {
        String valeur = request.getParameter(nomParametre);
        return valeur == null ? "" : valeur.trim();
    }

    private void verifierEntierPositif(String valeur, String champ, List<String> erreurs) {
        try {
            if (Integer.parseInt(valeur) <= 0) {
                erreurs.add(champ + " doit être un entier supérieur à zéro.");
            }
        } catch (NumberFormatException e) {
            erreurs.add(champ + " doit être un entier valide.");
        }
    }

    private void verifierTemperature(String valeur, List<String> erreurs) {
        try {
            double temperature = Double.parseDouble(valeur);
            if (!Double.isFinite(temperature) || temperature <= 0 || temperature > 99.99) {
                erreurs.add("La température doit être supérieure à 0 et inférieure ou égale à 99,99 °C.");
            }
        } catch (NumberFormatException e) {
            erreurs.add("La température doit être un nombre valide.");
        }
    }
}
