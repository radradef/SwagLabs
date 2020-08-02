package actions;

import entities.Credentials;
import pages.LoginPage;

public class LoginPageActions extends BaseActions {

    private LoginPage loginPage;
    private String defaultUser = "standard_user";
    private String getDefaultPass = "secret_sauce";

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

    public void logsInDefault(){
        fills(loginPage.usernameFld,
                defaultUser);
        fills(loginPage.passFld,
                getDefaultPass);
        clicks(loginPage.loginBtn);
    }
}
