package nl.achan.util.domain;

/**
 * Canonical data model for the submitted articles after processing.
 *
 * Created by Etienne on 10-6-2017.
 */
public class ArticleView {

    private long articleId;
    private String articleLink;
    private String adLink;

    public ArticleView() {
    }

    public ArticleView(long articleId, String articleLink, String adLink) {
        this.articleId = articleId;
        this.articleLink = articleLink;
        this.adLink = adLink;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public String getAdLink() {
        return adLink;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public void setArticle(String articleLink) {
        this.articleLink = articleLink;
    }

    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }

    @Override
    public String toString() {
        return "ArticleView{" +
                "articleId=" + articleId +
                ", articleLink='" + articleLink + '\'' +
                ", adLink='" + adLink + '\'' +
                '}';
    }
}
