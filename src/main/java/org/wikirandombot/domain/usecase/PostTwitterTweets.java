package org.wikirandombot.domain.usecase;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import twitter4j.*;

public class PostTwitterTweets {

    private PostTwitterTweets(){

    }

    private static final String CONSUMER_KEY  = "CONSUMER_KEY";
    private static final String CONSUMER_SECRET  = "CONSUMER_SECRET";
    private static final String ACCESS_TOKEN  = "ACCESS_TOKEN";
    private static final String ACCESS_TOKEN_SECRET  = "ACCESS_TOKEN_SECRET";
    private static final String BASE_URL = "https://api.twitter.com/2/tweets";

    public static void postTweet(String tweet) {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build()) {
            HttpPost httpPost = new HttpPost(BASE_URL);
            OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
            consumer.setTokenWithSecret(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
            consumer.sign(httpPost);

            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            JSONObject requestBodyJson = new JSONObject();
            requestBodyJson.put("text", tweet);

            String requestBody = requestBodyJson.toString();
            httpPost.setEntity(new StringEntity(requestBody, Charsets.UTF_8));

            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseString = EntityUtils.toString(response.getEntity());

            if (statusCode >= 200 && statusCode < 300) {
                System.out.println("Tweet posted: " + responseString);
            } else {
                System.out.println("Error posting tweet: " + responseString);
            }
        } catch (Exception e) {
            System.out.println("Exception posting tweet: " + e.getMessage());
        }
    }

}
