package ma.youcode.clinique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.youcode.clinique.db.DBConnection;
import ma.youcode.clinique.entity.Utilisateur;

public class JdbcUserDAO implements UserDAO {

    @Override
    public void save(Utilisateur user) {

        String sql = "INSERT INTO utilisateur (login, password, role)VALUES (?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
            ) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur of save",e);
        }
    }

    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        String sql = " SELECT id, login, password, role FROM utilisateur WHERE login = ?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            ResultSet result = statement.executeQuery();
            if (result.next()) {

                Utilisateur user = new Utilisateur(result.getLong("id"), result.getString("login"),
                        result.getString("password"), result.getString("role"));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur of serach user", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Utilisateur> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(Utilisateur user) {

    }

    @Override
    public void delete(Long id) {

    }

}
