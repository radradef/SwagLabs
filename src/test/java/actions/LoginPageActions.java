package actions;

import entities.Credentials;
import pages.LoginPage;

public class LoginPageActions extends BaseActions {

    private LoginPage loginPage;

    public LoginPageActions(LoginPage loginPage){
        super(loginPage.getDriver());
        this.loginPage = loginPage;
    }

    public void logsInWith(Credentials credentials){
        fills(loginPage.usernameFld,
                credentials.getUsername());
        fills(loginPage.passFld,
                credentials.getPassword());
        clicks(loginPage.loginBtn);
    }
}
