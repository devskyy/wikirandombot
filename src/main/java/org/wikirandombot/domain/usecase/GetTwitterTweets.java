package org.wikirandombot.domain.usecase;


import org.wikirandombot.domain.model.PromptTemplate;

import java.io.IOException;

public class GetTwitterTweets {

    private static String tweet;

    public static String generateTweet(String title, String text, String articleUrl) throws IOException {
        String prompt = PromptTemplate.getPrompt();
        String promptTitle = "Titulo del artículo: " + title + "\n";
        String promptText = "Texto artículo: " + text + "\n";
        String promptArticleUrl = articleUrl;

        prompt = prompt + promptTitle + promptText + promptArticleUrl;

        // Realizar la llamada a OpenAI API para generar la respuesta

        String response = GetOpenAiAPIConnection.generateConnection(prompt);


        tweet = response + "\n" + promptArticleUrl;

        return tweet;
    }

}
