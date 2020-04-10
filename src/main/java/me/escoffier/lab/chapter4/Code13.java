package me.escoffier.lab.chapter4;


import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import me.escoffier.superheroes.Character;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code13 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        Single<Character> random_heroes = client()
            .get("/heroes/random")
            .as(BodyCodec.json(Character.class))
            .rxSend()
            .map(HttpResponse::body);

        Single<Character> random_villains = client()
            .get("/villains/random")
            .as(BodyCodec.json(Character.class))
            .rxSend()
            .map(HttpResponse::body);

        // Associate the items emitted by both single and call the fight method.
        // In the subscribe print the returned json object (using encodePrettily).
        
        random_heroes
                .zipWith(random_villains, (hero, villain) -> fight(hero, villain))
                .subscribe(item -> System.out.println(item.encodePrettily()));
    }

    public static JsonObject fight(Character h, Character v) {
        String winner = h.getName();
        if (v.getSuperpowers().size() > h.getSuperpowers().size()) {
            winner = v.getName();
        } else if (v.getSuperpowers().size() == h.getSuperpowers().size()) {
            winner = "none";
        }
        return new JsonObject()
            .put("hero", h.getName())
            .put("villain", v.getName())
            .put("winner", winner);
    }
}
