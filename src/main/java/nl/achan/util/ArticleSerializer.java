package nl.achan.util;

import com.google.gson.Gson;
import nl.achan.util.domain.Article;
import nl.achan.util.domain.ArticleView;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Serializer for articles.
 *
 * Created by Etienne on 10-6-2017.
 */
public class ArticleSerializer {

    private final Gson jsonSerializer;
    private final Logger logger;

    public ArticleSerializer() {
        jsonSerializer = new Gson();
        logger = Logger.getLogger(ArticleSerializer.class.getName());
    }

    public Article fromMessage(Message message) {
        try {
            TextMessage textMessage = ((TextMessage) message);
            String text = textMessage.getText();
            Article article = jsonSerializer.fromJson(text, Article.class);
            return article;
        } catch (JMSException e){
            logger.log(Level.SEVERE, "Failed to parse message! :(");
            e.printStackTrace();
            return null;
        }
    }

    public Article fromJson(String article){
        Article result = jsonSerializer.fromJson(article, Article.class);
        logger.log(Level.INFO, "Parsed article: " + result.toString());
        return result;
    }

    public String toJson(Article article) {
        String result = jsonSerializer.toJson(article);
        logger.log(Level.INFO, "Parsed article: " + result);
        return jsonSerializer.toJson(article);
    }

    public String toJson(ArticleView articleView){
        String result = jsonSerializer.toJson(articleView);
        logger.log(Level.INFO, "Parsed articleView: " + result);
        return jsonSerializer.toJson(articleView);
    }
}
