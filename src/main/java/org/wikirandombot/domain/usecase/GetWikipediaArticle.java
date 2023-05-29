package org.wikirandombot.domain.usecase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.wikirandombot.domain.model.Article;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWikipediaArticle {

    public static Article getRandomArticleData() throws IOException {
        // Hacer una solicitud HTTP GET para obtener un artículo aleatorio
        URL url = new URL("https://es.wikipedia.org/api/rest_v1/page/random/summary");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Leer la respuesta JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = mapper.readTree(con.getInputStream());

        // Extraer el título del artículo aleatorio
        String title = response.get("title").asText();

        // Extraer el texto del artículo
        String text = response.get("extract").asText();

        // Extraer la URL de la imagen principal del artículo
        String imageUrl = null;
        if (response.has("originalimage")) {
            imageUrl = response.get("originalimage").get("source").asText();
        }

        // Construir la URL del artículo
        String articleUrl = response.get("content_urls").get("desktop").get("page").asText();
        StringBuilder encodedURL = new StringBuilder();


        for (int i = 0; i < articleUrl.length(); i++) {
            char c = articleUrl.charAt(i);
            if (c == '(') {
                encodedURL.append("%28");
            } else if (c == ')') {
                encodedURL.append("%29");
            } else if (c == '\'') {
                encodedURL.append("%27");
            } else if (c == '<') {
                encodedURL.append("%3C");
            } else if (c == '>') {
                encodedURL.append("%3E");
            } else if (c == '#') {
                encodedURL.append("%23");
            } else if (c == '&') {
                encodedURL.append("%26");
            } else {
                encodedURL.append(c);
            }
        }

        articleUrl = encodedURL+"";

        return new Article(title, text, imageUrl, articleUrl);
    }
}
