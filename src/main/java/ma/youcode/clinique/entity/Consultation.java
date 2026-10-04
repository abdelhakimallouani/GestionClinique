package ma.youcode.clinique.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Consultation {

    private Long id;
    private Long patientId;

    private String motif;
    private String observations;
    private String diagnostic;
    private String traitement;
    private BigDecimal cout;
    private LocalDateTime dateConsultation;
    private String statut;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Long patientId) {
        this.patientId = patientId;
        this.cout = new BigDecimal("150.00");
        this.statut = "EN_ATTENTE";
    }

    public Consultation(Long id, Long patientId, String motif, String observations, String diagnostic,
            String traitement, BigDecimal cout, LocalDateTime dateConsultation, String statut) {

        this.id = id;
        this.patientId = patientId;
        this.motif = motif;
        this.observations = observations;
        this.diagnostic = diagnostic;
        this.traitement = traitement;
        this.cout = cout;
        this.dateConsultation = dateConsultation;
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}