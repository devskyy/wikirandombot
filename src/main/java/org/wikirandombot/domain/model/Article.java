package org.wikirandombot.domain.model;

public class Article {

    private String title;
    private String text;
    private String imageUrl;
    private String articleUrl;

    public Article(String title, String text, String imageUrl, String articleUrl) {
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.articleUrl = articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }
}
