package org.wikirandombot;

import org.wikirandombot.domain.model.Article;
import org.wikirandombot.domain.usecase.GetWikipediaArticle;
import org.wikirandombot.domain.usecase.PostTwitterTweets;

import java.io.IOException;

import static org.wikirandombot.domain.usecase.GetTwitterTweets.generateTweet;
import static org.wikirandombot.domain.usecase.GetWikipediaArticle.getRandomArticleData;

public class Main {
    public static void main(String[] args) throws IOException {

        // Obtener los datos del artículo aleatorio
        Article articleData = getRandomArticleData();

        //Obtener el tweet y la imagen a partir de los parámetros
        String tweet = generateTweet(articleData.getTitle(), articleData.getText(), articleData.getArticleUrl());
        System.out.println(articleData.getImageUrl());

        //Publicar el tweet
        System.out.println("El tweet obtenido: " + tweet);
        PostTwitterTweets.postTweet(tweet);
    }
}