package stepDefinitions.config;

import entities.Credentials;
import io.cucumber.java.DataTableType;
import java.util.Map;

public class DataTableTypes {

    @DataTableType
    public Credentials credentialsOutlineEntry(Map<String, String> entry){
        Credentials credentials = new Credentials();
        credentials.setUsername(entry.get("username"));
        credentials.setPassword(entry.get("password"));

        return credentials;
    }
}
