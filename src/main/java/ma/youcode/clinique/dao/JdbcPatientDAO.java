package ma.youcode.clinique.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinique.db.DBConnection;
import ma.youcode.clinique.entity.Patient;

public class JdbcPatientDAO implements PatientDAO {

    @Override
    public void save(Patient patient) {
        String sql = "INSERT INTO patient (nom, prenom, date_naissance, numero_securite_sociale, "
                + "tension_arterielle, frequence_cardiaque, temperature, frequence_respiratoire, heure_arrivee) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getNom());
            statement.setString(2, patient.getPrenom());
            statement.setDate(3, Date.valueOf(patient.getDateNaissance()));
            statement.setString(4, patient.getNumeroSecuriteSociale());
            statement.setString(5, patient.getTensionArterielle());
            statement.setInt(6, patient.getFrequenceCardiaque());
            statement.setDouble(7, patient.getTemperature());
            statement.setInt(8, patient.getFrequenceRespiratoire());
            statement.setTimestamp(9, Timestamp.valueOf(patient.getHeureArrivee()));
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    patient.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Erreur pendant l'enregistrement du patient", e);
        }
    }

    @Override
    public Optional<Patient> findById(Long id) {
        String sql = "SELECT * FROM patient WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(mapPatient(result));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Erreur pendant la recherche du patient", e);
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    patients.add(mapPatient(result));
                }
            }
            return patients;
        } catch (SQLException e) {
            throw new IllegalStateException("Erreur pendant la liste des patients", e);
        }
    }

    private Patient mapPatient(ResultSet result) throws SQLException {
        Patient patient = new Patient();
        patient.setId(result.getLong("id"));
        patient.setNom(result.getString("nom"));
        patient.setPrenom(result.getString("prenom"));
        patient.setDateNaissance(result.getDate("date_naissance").toLocalDate());
        patient.setNumeroSecuriteSociale(result.getString("numero_securite_sociale"));
        patient.setTensionArterielle(result.getString("tension_arterielle"));
        patient.setFrequenceCardiaque(result.getInt("frequence_cardiaque"));
        patient.setTemperature(result.getDouble("temperature"));
        patient.setFrequenceRespiratoire(result.getInt("frequence_respiratoire"));
        patient.setHeureArrivee(result.getTimestamp("heure_arrivee").toLocalDateTime());
        return patient;
    }
}
