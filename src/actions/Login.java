package actions;

import com.opensymphony.xwork2.ActionSupport;
//import enty.UserDAO;
import enty.UserDAO;
import hibernate.HibernateSessionFactory;
import org.hibernate.Session;

public class Login extends ActionSupport implements STATUS {
    int status;
    String email;
    String password;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {

        UserDAO userDAO = new UserDAO();
        status = userDAO.userAuthentication(email, password) ? STATUS.SUCCESS : STATUS.FAILED;
        return "result";
    }
}
