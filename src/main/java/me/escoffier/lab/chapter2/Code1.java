package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Code1 {

    private static List<String> SUPER_HEROES = Arrays.asList(
            "Superman",
            "Batman",
            "Aquaman",
            "Asterix",
            "Captain America"
    );

    public static void main(String... args) {
        Observable.fromIterable(SUPER_HEROES)
                // Use doOnNext and doOnComplete to print messages
                // on each item and when the stream complete
                .doOnNext(item -> System.out.println("Next >> " + item))
                .doOnComplete(() -> System.out.println("Completion"))
                .subscribe();
    }
}
