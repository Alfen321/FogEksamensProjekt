package User.Logic;

import Database.DataAccessObjectImpl;
import User.AdminUser;
import User.Password;
import User.User;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Login {

    DataAccessObjectImpl DAO;
    Password pass = new Password();

    public Login() throws Exception {
        this.DAO = new DataAccessObjectImpl();
    }

    public User returnUser(String username) throws SQLException {
        return DAO.getUserByUsername(username);
    }

    public AdminUser returnAdminUser(String username) throws SQLException {
        return DAO.getAdminByUsername(username);
    }

    public boolean passwordCheck(String username, String password) throws SQLException, UnsupportedEncodingException {
        User user = DAO.getUserByUsername(username);

        if (user != null) {
            if (user.getHashedPW().equals(pass.get_SHA_512_SecurePassword(password, user.getSalt()))) {
                return true;
            }
        }
        return false;
    }

    public boolean adminPasswordCheck(String username, String password) throws SQLException, UnsupportedEncodingException {
        //DAO.createAdmin("Admin", "Admin", "1", "Test Nejsen");
        AdminUser user = DAO.getAdminByUsername(username);

        if (user != null) {
            if (user.getHashedPW().equals(pass.get_SHA_512_SecurePassword(password, user.getSalt()))) {
                return true;
            }
        }
        return false;
    }

}
