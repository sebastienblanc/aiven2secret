package org.sebi;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


/**
 * To use it via injection.
 *
 * {@code
 *     @Inject
 *     @RestClient
 *     MyRemoteService myRemoteService;
 *
 *     public void doSomething() {
 *         Set<MyRemoteService.Extension> restClientExtensions = myRemoteService.getExtensionsById("io.quarkus:quarkus-rest-client");
 *     }
 * }
 */
@RegisterRestClient(baseUri = "https://api.aiven.io/v1/project")
public interface MyRemoteService {

    @GET
    @Path("/{project}/service/{serviceName}")
    JsonObject convert(@HeaderParam("Authorization") String authHeader, @PathParam("project") String project, @PathParam("serviceName") String serviceName);

}
