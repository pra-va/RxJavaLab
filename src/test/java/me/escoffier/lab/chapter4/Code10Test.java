package me.escoffier.lab.chapter4;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import me.escoffier.superheroes.SuperHeroesService;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static me.escoffier.lab.chapter4.Code10.getHero;


public class Code10Test {

    private final String yoda = "{\n" +
            "  \"id\" : 726,\n" +
            "  \"name\" : \"Yoda\",\n" +
            "  \"superpowers\" : [ \"The Force\", \"Cloaking\", \"Empathy\", \"Precognition\", \"Danger Sense\" ],\n" +
            "  \"villain\" : false\n" +
            "}";

    @Test
    public void getHeroShouldReturnCorrectHero() throws InterruptedException {
        SuperHeroesService.run();

        Single<String> hero = getHero("726").map(item -> item.encodePrettily());

        TestObserver<String> testObserver = hero.test();

        testObserver
                .assertNoValues()
                .assertNoErrors();

        testObserver.await(200, TimeUnit.MILLISECONDS);

        testObserver
                .assertNoErrors()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(yoda);
    }

    @Test
    public void getHeroByNameShouldReturnException() throws InterruptedException {
        SuperHeroesService.run();

        TestObserver<String> hero = getHero("Yoda")
                .map(item -> item.encodePrettily())
                .test();

        hero.await(200, TimeUnit.MILLISECONDS);

        hero.assertValueCount(0);

    }

}