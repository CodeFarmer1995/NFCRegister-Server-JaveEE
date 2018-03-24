package actions;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionSupport;
import enty.User;
import enty.UserDAO;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SignUp extends ActionSupport implements STATUS {
    User user;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() {

        System.out.println("user"+user);
        UserDAO userDAO = new UserDAO();
        if (userDAO.register(user))
            status = STATUS.SUCCESS;
        else
            status = STATUS.EMAIL_EXISTED;
        return "result";
    }
}
