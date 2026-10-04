package ma.youcode.clinique.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
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
        LocalDate aujourdHui = LocalDate.now();
        return patientDAO.findAll().stream()
                .filter(patient -> patient.getHeureArrivee() != null
                        && patient.getHeureArrivee().toLocalDate().equals(aujourdHui))
                .sorted(Comparator.comparing(Patient::getHeureArrivee))
                .toList();
    }

    public Optional<Patient> findById(Long id) {
        return patientDAO.findById(id);
    }
}
