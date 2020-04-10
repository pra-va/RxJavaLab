package me.escoffier.lab.chapter3;


import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code5 {


    public static void main(String[] args) {
        SuperHeroesService.run();
        String name1 = "Yoda";
        String name2 = "clement";

        client().get("/heroes").rxSend()
                .map(HttpResponse::bodyAsJsonObject)
                // Use the filter operator and contains to check if the is a hero named `name1`
                .filter(item -> contains(name1, item))
                .subscribe(item -> System.out.println("Yes, " + name1 + " is in the list."),
                        Throwable::printStackTrace,
                        () -> System.out.println("No such hero as " + name1 + " was found in the list."))

        // Don't forget to subscribe
        ;


        client().get("/heroes").rxSend()
                .map(HttpResponse::bodyAsJsonObject)
        // Use the filter operator and contains to check if the is a hero named `name2`
                .filter(item -> contains(name2, item))
                .subscribe(item -> System.out.println("Yes, " + name2 + " is in the list."),
                        Throwable::printStackTrace,
                        () -> System.out.println("No such hero as " + name2 + " was found in the list."))
        // Don't forget to subscribe
        ;
    }

    private static boolean contains(String name, JsonObject json) {
        return json.stream().anyMatch(e -> e.getValue().toString().equalsIgnoreCase(name));
    }
}
