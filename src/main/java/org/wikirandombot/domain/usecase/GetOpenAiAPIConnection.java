package org.wikirandombot.domain.usecase;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetOpenAiAPIConnection {
    private static final String OPENAPIURL = "https://api.openai.com/v1/completions";
    private static  final String BEARER = "Bearer BEARER_KEY";

    public static String generateConnection(String prompt) throws IOException {
        int max_tokens = 350;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(OPENAPIURL);

        JSONObject requestData = new JSONObject();
        requestData.put("model", "text-davinci-003");
        requestData.put("prompt", prompt);
        requestData.put("max_tokens", max_tokens);
        requestData.put("temperature", 0);

        StringEntity params = new StringEntity(requestData.toString(), StandardCharsets.UTF_8);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", BEARER);
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity);

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(responseContent, JsonObject.class);
        JsonArray choicesArray = jsonResponse.getAsJsonArray("choices");

        String text = "";
        if (choicesArray.size() > 0) {
            JsonObject choice = choicesArray.get(0).getAsJsonObject();
            text = choice.get("text").getAsString();
        } else {
            System.out.println("No se encontraró respuesta");
        }

        return text;
    }
}
