package ma.youcode.clinique.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.clinique.entity.Consultation;

public interface ConsultationDAO extends DAO<Consultation> {
    List<Consultation> findByStatut(String statut);
    // List<Consultation> findPending();
    // Optional<Consultation> findByPatientId(Long patientId);
    
}
