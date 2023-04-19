package backend.siptis.service.records;

import org.springframework.stereotype.Service;

@Service
public class WppService {
    public void sendWpp(String message) {
        System.out.println("Enviando mensaje a Whatsapp: " + message);
        /*final String uri = "";

        RestTemplate restTemplate = new RestTemplate();

        String reqBody = "{"city": "Ranchi"}";
        String result = restTemplate.postForObject(uri, reqBody, String.class);

        // convert your result into json

        try {
            jsonResponse = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //extract a value "name" from your json data:
        try{
            String value = jsonResponse.getString("name");
        }catch(JSONException e) {
            e.printStackTrace();
        }*/
    }
}
