package ma.youcode.clinique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import ma.youcode.clinique.entity.Patient;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import ma.youcode.clinique.db.DBConnection;
import ma.youcode.clinique.entity.Consultation;

public class JdbcConsultationDAO implements ConsultationDAO {
    @Override
    public void save(Consultation consultation) {
        String sql = "INSERT INTO consultation (patient_id,statut)VALUES (?, ?)";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, consultation.getPatientId());

            statement.setString(2, consultation.getStatut());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                consultation.setId(result.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur of save consultation", e);
        }
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        String sql = " SELECT c.id, c.patient_id, c.motif, c.observations, c.diagnostic, c.traitement, c.cout, c.date_consultation, c.statut FROM consultation c WHERE c.id = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {

                Consultation consultation = new Consultation();
                consultation.setId(result.getLong("id"));
                consultation.setPatientId(result.getLong("patient_id"));
                consultation.setMotif(result.getString("motif"));
                consultation.setObservations(result.getString("observations"));
                consultation.setDiagnostic(result.getString("diagnostic"));
                consultation.setTraitement(result.getString("traitement"));
                consultation.setCout(result.getBigDecimal("cout"));
                if (result.getTimestamp("date_consultation") != null) {

                    consultation.setDateConsultation(
                            result.getTimestamp("date_consultation")
                                    .toLocalDateTime());
                }
                consultation.setStatut(result.getString("statut"));
                return Optional.of(consultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur of find consultation by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Consultation> findByStatut(String statut) {
        String sql = "SELECT c.id AS id, c.patient_id AS patient_id, p.nom AS nom, p.prenom AS prenom, c.statut AS statut FROM consultation c JOIN patient p ON c.patient_id = p.id WHERE c.statut = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, statut);
            ResultSet result = statement.executeQuery();

            List<Consultation> consultations = new ArrayList<>();
            while (result.next()) {
                Patient patient = new Patient();
                patient.setId(result.getLong("patient_id"));
                patient.setNom(result.getString("nom"));
                patient.setPrenom(result.getString("prenom"));
                Consultation consultation = new Consultation();
                consultation.setId(result.getLong("id"));
                consultation.setPatientId(result.getLong("patient_id"));
                consultation.setStatut(result.getString("statut"));
                consultation.setPatient(patient);
                consultations.add(consultation);
            }
            return consultations;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur of find all consultations", e);
        }
    }

    @Override
    public void update(Consultation consultation) {
        String sql = "UPDATE consultation SET motif = ?, observations = ?, diagnostic = ?, traitement = ?, cout = ?, date_consultation = ?, statut = ? WHERE id = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, consultation.getMotif());
            statement.setString(2, consultation.getObservations());
            statement.setString(3, consultation.getDiagnostic());
            statement.setString(4, consultation.getTraitement());
            statement.setBigDecimal(5, consultation.getCout());
            statement.setTimestamp(6, Timestamp.valueOf(consultation.getDateConsultation()));
            statement.setString(7, consultation.getStatut());
            statement.setLong(8, consultation.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur of update consultation", e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
