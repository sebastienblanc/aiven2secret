package org.sebi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.qute.Engine;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "aiven2secret", mixinStandardHelpOptions = true)
public class ConvertCommand implements Runnable {

    @Location("secret.yml") 
    Template secret;

    @Inject
    Engine engine; 

    @RestClient
    MyRemoteService remoteService;

    @Parameters(paramLabel = "<project>", defaultValue = "project",
        description = "Your project.")
    String project;

    @Parameters(paramLabel = "<service>", defaultValue = "service",
        description = "Your service.")
    String service;

    @CommandLine.Option(names = {"-t", "--token"}, description = "Auth token", defaultValue = "token")
    String token;

    @Override
    public void run() {
        String authToken = "Bearer " + token;
        JsonObject jsonObject = remoteService.convert(authToken, project, service);
        JsonObject details = jsonObject.getJsonObject("service").getJsonObject("service_uri_params");
        ConnectionDetails connectionDetails = new ConnectionDetails(details.getString("host"), details.getString("port"), details.getString("user"), details.getString("password"),details.getString("dbname"),details.getString("sslmode"),jsonObject.getJsonObject("service").getString("service_name"));
         Path path = Paths.get(jsonObject.getJsonObject("service").getString("service_name") + "-secret.yml");
         byte[] strToBytes = secret.data(connectionDetails).render().getBytes();

        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
