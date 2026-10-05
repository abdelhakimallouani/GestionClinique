package ma.youcode.clinique.service;

import java.math.BigDecimal;

import ma.youcode.clinique.dao.ConsultationDAO;
import ma.youcode.clinique.entity.Consultation;

public class ConsultationService {
    private final ConsultationDAO consultationDAO;
    
    public ConsultationService(ConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }
    public void enregistrer(Consultation consultation) {
        if (consultation.getCout() == null) {
            consultation.setCout(new BigDecimal("0.00"));
        }
        if (consultation.getStatut() == null) {
            consultation.setStatut("EN_ATTENTE");
        }
        consultationDAO.save(consultation);
    }
}
