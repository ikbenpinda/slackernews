package nl.achan.aggregate.subscribers;

import nl.achan.aggregate.Categories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * A simple command-line interface for the Subscriber API.
 *
 * Created by Etienne on 11-6-2017.
 */
public class SubscriberCLI {

    public static void main(String[] args) throws IOException {

        Subscriber subscriber = new UserSubscriber();
        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("Subscriber CLI ---------------");
            System.out.println("Subscribe to topic? Y/N");
            input = in.readLine();
            if (input.toUpperCase().equals("Y")) {
                System.out.println("Topic:");
                Categories[] values = Categories.values();
                for (int i = 0; i < values.length; i++) {
                    Categories category = values[i];
                    System.out.println(i + " = " + values[i]);
                }
                int categoryIndex = Integer.parseInt(in.readLine());
                String topic = values[categoryIndex].name();
                subscriber.subscribe(topic, article -> {
                    System.out.println("Article received: " + article.toString());
                });
                System.out.println("Subscribe to topic " + topic + ", new articles will be shown automatically.");
            } else {
                System.out.println("Exit application? Y/N");
                input = in.readLine();
                if (input.toUpperCase().equals("Y"))
                    return;
            }
        }
    }
}
