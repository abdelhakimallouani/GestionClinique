package ma.youcode.clinique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public void update(Consultation consultation) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
