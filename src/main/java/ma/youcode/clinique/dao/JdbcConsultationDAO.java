package ma.youcode.clinique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import ma.youcode.clinique.entity.Patient;
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Consultation> findByStatut(String statut) {
        String sql = "SELECT c.id AS consultation_id, c.patient_id AS patient_id, p.nom AS nom, p.prenom AS prenom, c.statut AS statut FROM consultation c JOIN patient p ON c.patient_id = p.id WHERE c.statut = ?";
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
                consultation.setId(result.getLong("consultation_id"));
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

            statement.setLong(1, consultation.getPatientId());
            statement.setString(2, consultation.getStatut());
            statement.setLong(3, consultation.getId());
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
