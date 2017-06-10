package nl.achan.aggregate;

import com.google.gson.Gson;
import nl.achan.aggregate.interfaces.Article;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * One-way deserializer for articles.
 *
 * Created by Etienne on 10-6-2017.
 */
public class ArticleSerializer {

    Gson jsonSerializer;

    public ArticleSerializer() {
        jsonSerializer = new Gson();
    }

    public Article fromMessage(Message message) {
        try {
            TextMessage textMessage = ((TextMessage) message);
            String text = textMessage.getText();
            Article article = jsonSerializer.fromJson(text, Article.class);
            return article;
        } catch (JMSException e){
            Logger.getLogger(ArticleSerializer.class.getName()).log(Level.SEVERE, "Failed to parse message! :(");
            e.printStackTrace();
            return null;
        }
    }

    public String toMessage(Article article) {
        return jsonSerializer.toJson(article);
    }
}
