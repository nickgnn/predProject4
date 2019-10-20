package util;

import exception.DBException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private boolean isLoggedOn;
    private String name;
    private String password;

    private String requestedPage;

    public boolean isLoggedOn() {
        return isLoggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        isLoggedOn = loggedOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }

    public void logIn() throws DBException, IOException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            isLoggedOn = false;
            return;
        }

        isLoggedOn = AuthManager.isLogin(name, password);


        System.out.println(isLoggedOn);
    }
}
