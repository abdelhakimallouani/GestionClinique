package ma.youcode.clinique.service;

import java.math.BigDecimal;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinique.dao.ConsultationDAO;
import ma.youcode.clinique.entity.Consultation;
import java.time.LocalDateTime;

public class ConsultationService {
    private final ConsultationDAO consultationDAO;
    
    public ConsultationService(ConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }

    public List<Consultation> lister() {
        return consultationDAO.findByStatut("EN_ATTENTE");
    }
    public Optional<Consultation> findById(Long id) {
        return consultationDAO.findById(id);
    }
    public void closeConsultation(Long id, String motif, String observations, String diagnostic, String traitement) {
        Optional<Consultation> consultationOpt = consultationDAO.findById(id);
        if (consultationOpt.isPresent()) {
            Consultation consultation = consultationOpt.get();
            consultation.setMotif(motif);
            consultation.setObservations(observations);
            consultation.setDiagnostic(diagnostic);
            consultation.setTraitement(traitement);
            consultation.setCout(new BigDecimal("150.00"));
            consultation.setDateConsultation(LocalDateTime.now());
            consultation.setStatut("TERMINEE");
            consultationDAO.update(consultation);
        } else {
            throw new IllegalArgumentException("Consultation with ID " + id + " not found.");
        }
    }
}
