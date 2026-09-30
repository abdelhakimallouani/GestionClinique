package ma.youcode.clinique.dao;
import ma.youcode.clinique.entity.Utilisateur;
import java.util.Optional;

public interface UserDAO extends DAO<Utilisateur> {
    Optional<Utilisateur> findByLogin(String login);
}
