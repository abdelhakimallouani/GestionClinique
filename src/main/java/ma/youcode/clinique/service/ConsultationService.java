package ma.youcode.clinique.service;

import java.math.BigDecimal;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

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
    public Optional<Consultation> findById(Long id) {
        return consultationDAO.findById(id);
    }
}
