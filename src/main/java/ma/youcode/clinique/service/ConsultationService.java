package ma.youcode.clinique.service;

import java.math.BigDecimal;
import java.util.List;

import ma.youcode.clinique.dao.ConsultationDAO;
import ma.youcode.clinique.entity.Consultation;

public class ConsultationService {
    private final ConsultationDAO consultationDAO;
    
    public ConsultationService(ConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }

    public List<Consultation> lister() {
        return consultationDAO.findByStatut("EN_ATTENTE");
    }
}
