package ma.youcode.clinique.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinique.dao.JdbcPatientDAO;
import ma.youcode.clinique.dao.PatientDAO;
import ma.youcode.clinique.entity.Patient;

public class PatientService {
    private final PatientDAO patientDAO = new JdbcPatientDAO();

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
        return patientDAO.findByArrivalDate(LocalDate.now());
    }
}
