package ca.polymtl.metafy;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import ca.polymtl.metafy.ApiKeyLoader;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target("https://accounts.spotify.com/api/token");

        Form form = new Form();
        form.param("grant_type", "client_credentials");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        request.header("Authorization", "Basic " + ApiKeyLoader.getInstance().getApiKey("Spotify"));
        AuthorizationReturnDTO response = request.post(Entity.form(form), AuthorizationReturnDTO.class);

        System.out.println(response);

        resource = client.target("https://api.spotify.com/v1/search?q=adele&type=track&limit=10");

        Invocation.Builder request2 = resource.request();
        request2.accept(MediaType.APPLICATION_JSON);
        request2.header("Authorization", "Bearer " + response.getAccessToken());
        SearchReturnDTO response2 = request2.get(SearchReturnDTO.class);

        System.out.println(response2);
        /*if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity().toString());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
        }*/
    }

}
