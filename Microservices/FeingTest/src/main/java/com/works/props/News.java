package com.works.props;
import java.util.List;

public class News {
    private String status;
    private Long totalResults;
    private List<Article> articles;

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Long getTotalResults() { return totalResults; }
    public void setTotalResults(Long value) { this.totalResults = value; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> value) { this.articles = value; }
}