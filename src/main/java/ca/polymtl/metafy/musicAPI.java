package ca.polymtl.metafy;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import ca.polymtl.metafy.ApiKeyLoader;

public class musicAPI {

    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        /*WebTarget resource = client.target("http://api.jamendo.com/v3.0");

        Form form = new Form();
        form.param("grant_type", "client_credentials");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        request.header("Authorization", "Basic " + ApiKeyLoader.getInstance().getApiKey("Jamendo"));
        AuthorizationReturnDTO response = request.post(Entity.form(form), AuthorizationReturnDTO.class);*/

        //System.out.println(response);

        WebTarget resource = client.target("https://api.jamendo.com/v3.0/tracks/?client_id=dacd3181&format=jsonpretty&limit=2&fuzzytags=groove+rock&speed=high+veryhigh&include=musicinfo&groupby=artist_id");

        Invocation.Builder request2 = resource.request();
        request2.accept(MediaType.APPLICATION_JSON);
        //request2.header("Authorization", "Bearer " + response.getAccessToken());
        //SearchReturnDTO response2 = request2.get(SearchReturnDTO.class);
        System.out.println(request2.get().readEntity(String.class));

        //System.out.println(response2);
        /*if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity().toString());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
        }*/
    }

}
