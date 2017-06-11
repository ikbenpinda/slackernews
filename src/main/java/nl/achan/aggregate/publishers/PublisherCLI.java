package nl.achan.aggregate.publishers;

import nl.achan.aggregate.Categories;
import nl.achan.aggregate.interfaces.Article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * A simple command-line interface for the publisher API.
 *
 * Created by Etienne on 11-6-2017.
 */
public class PublisherCLI {

    public static void main(String[] args) throws IOException {

        Publisher publisher = new InternalPublisher();
        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true) {

            System.out.println("Publisher CLI ----------------");
            System.out.println("Publish new article? Y/N");
            input = in.readLine();
            if (input.toUpperCase().equals("Y")) {
                System.out.println("Id?");
                long id = Long.parseLong(in.readLine());
                System.out.println("Link?");
                String link = in.readLine();
                System.out.println("Publisher");
                String publisherName = in.readLine();
                System.out.println("Category?");
                Categories[] values = Categories.values();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(i + " = " + values[i].name());
                }
                int categoryIndex = Integer.parseInt(in.readLine());
                Article article = new Article(id, link, publisherName, values[categoryIndex]);

                System.out.println("Publishing article: " + article.toString());

                publisher.publish(article);
            }
        }
    }
}
