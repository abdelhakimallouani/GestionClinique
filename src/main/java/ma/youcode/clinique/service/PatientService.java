package ma.youcode.clinique.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import ma.youcode.clinique.dao.ConsultationDAO;
import ma.youcode.clinique.dao.JdbcConsultationDAO;

import ma.youcode.clinique.dao.PatientDAO;
import ma.youcode.clinique.entity.Consultation;
import ma.youcode.clinique.entity.Patient;

public class PatientService {
    private final PatientDAO patientDAO;
    private final ConsultationDAO consultationDAO;

    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = Objects.requireNonNull(patientDAO);
        this.consultationDAO = new JdbcConsultationDAO();
    }

    public PatientService(PatientDAO patientDAO, ConsultationDAO consultationDAO) {
        this.patientDAO = Objects.requireNonNull(patientDAO);
        this.consultationDAO = Objects.requireNonNull(consultationDAO);
    }

    public void enregistrer(Patient patient) {
        if (patient.getHeureArrivee() == null) {
            patient.setHeureArrivee(LocalDateTime.now());
        }
        patientDAO.save(patient);
        Long patientId = patient.getId();
        Consultation consultation = new Consultation(patientId);
        consultationDAO.save(consultation);
    }

    public Optional<Patient> chercher(Long id) {
        return patientDAO.findById(id);
    }

    public List<Patient> listerPatientsDuJour() {
        return listerPatientsParDate(LocalDate.now());
    }

    public List<Patient> listerPatientsParDate(LocalDate date) {
        return patientDAO.findByArrivalDate(Objects.requireNonNull(date));
    }

    public List<Patient> listerPatientsParDate(LocalDate date, String tri, String ordre) {
        List<Patient> patients = new ArrayList<>(listerPatientsParDate(date));
        boolean descendant = "desc".equals(ordre);
        Comparator<String> texte = descendant
                ? String.CASE_INSENSITIVE_ORDER.reversed() : String.CASE_INSENSITIVE_ORDER;
        Comparator<Patient> comparateur = switch (tri == null ? "arrivee" : tri) {
            case "nom" -> Comparator.comparing(Patient::getNom, Comparator.nullsLast(texte))
                    .thenComparing(Patient::getPrenom, Comparator.nullsLast(texte));
            case "naissance" -> Comparator.comparing(Patient::getDateNaissance,
                    Comparator.nullsLast(descendant ? Comparator.<LocalDate>reverseOrder()
                            : Comparator.<LocalDate>naturalOrder()));
            default -> Comparator.comparing(Patient::getHeureArrivee,
                    Comparator.nullsLast(descendant ? Comparator.<LocalDateTime>reverseOrder()
                            : Comparator.<LocalDateTime>naturalOrder()));
        };
        patients.sort(comparateur.thenComparing(Patient::getId,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return patients;
    }

    public Optional<Patient> findById(Long id) {
        return patientDAO.findById(id);
    }
}
