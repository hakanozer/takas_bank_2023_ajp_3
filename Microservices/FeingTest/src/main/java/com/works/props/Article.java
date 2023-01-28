package com.works.props;

import java.time.OffsetDateTime;

public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private OffsetDateTime publishedAt;
    private String content;

    public Source getSource() { return source; }
    public void setSource(Source value) { this.source = value; }

    public String getAuthor() { return author; }
    public void setAuthor(String value) { this.author = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getURLToImage() { return urlToImage; }
    public void setURLToImage(String value) { this.urlToImage = value; }

    public OffsetDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(OffsetDateTime value) { this.publishedAt = value; }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }
}
