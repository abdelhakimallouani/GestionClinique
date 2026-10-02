package ma.youcode.clinique.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import ma.youcode.clinique.entity.Patient;

public interface PatientDAO {
    void save(Patient patient);
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
    List<Patient> findByArrivalDate(LocalDate date);
}
