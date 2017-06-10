package nl.achan.aggregate;

import com.google.gson.Gson;
import junit.framework.Assert;
import nl.achan.aggregate.interfaces.Article;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test article serialization to and from JSON.
 *
 * Created by Etienne on 10-6-2017.
 */
public class ArticleSerializerTest {

    @Test
    public void testSerialization() {

        ArticleSerializer serializer = new ArticleSerializer();
        Article article = new Article(0, "example.com/1234", "Reuters", Categories.POLITICS);

        String json = serializer.toJson(article);
        System.out.println(json);
        assertNotNull(json);
        assertFalse(json.isEmpty());

        Article article2 = serializer.fromJson(json);
        System.out.println(article2);
        assertNotNull(article2);
        assertEquals(article, article2);
    }
}