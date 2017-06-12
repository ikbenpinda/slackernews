package nl.achan.util.domain;

import nl.achan.util.Categories;

/**
 * Canonical data model for any articles submitted.
 *
 * Created by Etienne on 9-6-2017.
 */
public class Article {

    private long articleId = 0;
    private String link;
    private String publisher;
    private Categories category;

    public Article(long articleId, String link, String publisher, Categories category) {
        this.articleId = articleId;
        this.link = link;
        this.publisher = publisher;
        this.category = category;
    }

    public long getArticleId(){
        return articleId;
    };

    public String getArticleLink() {
        return link;
    }

    public String getPublisherName(){
        return publisher;
    };

    public String getCategory(){
        return category.name();
    };

    @Override
    public boolean equals(Object obj) {
        Article other = (Article) obj;
        if (other.getArticleId() != articleId)
            return false;
        if (!other.getArticleLink().equals(link))
            return false;
        if (!other.getPublisherName().equals(publisher))
            return false;
        if (!other.getCategory().equals(category.name()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", link='" + link + '\'' +
                ", publisher='" + publisher + '\'' +
                ", category=" + category +
                '}';
    }
}
