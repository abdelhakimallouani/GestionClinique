package ma.youcode.clinique.service;

import java.nio.file.OpenOption;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import ma.youcode.clinique.dao.UserDAO;
import ma.youcode.clinique.entity.Utilisateur;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Optional<Utilisateur> login (String login, String password){
        Optional<Utilisateur> optionalUser = userDAO.findByLogin(login);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        Utilisateur user = optionalUser.get();

        boolean passwordCorrect = BCrypt.checkpw(password,user.getPassword());

        if (!passwordCorrect) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
