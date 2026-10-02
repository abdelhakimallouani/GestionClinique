package ma.youcode.clinique.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ma.youcode.clinique.dao.PatientDAO;
import ma.youcode.clinique.entity.Patient;

public class PatientService {
    private final PatientDAO patientDAO;

    public PatientService(PatientDAO patientDAO) {
        this.patientDAO = Objects.requireNonNull(patientDAO);
    }

    public void enregistrer(Patient patient) {
        if (patient.getHeureArrivee() == null) {
            patient.setHeureArrivee(LocalDateTime.now());
        }
        patientDAO.save(patient);
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
}
