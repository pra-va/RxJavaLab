package me.escoffier.lab.chapter4;


import java.util.HashSet;

import static me.escoffier.superheroes.Helpers.heroes;

public class Code6 {

    public static void main(String[] args) {
        heroes()
            // Build a set containing all the super power from the heroes
            // This exercise uses the `scan` method
                .scan(new HashSet<>(), (set, powers) -> {
                    set.addAll(powers.getSuperpowers());
                    return set;
                })
            
            .doOnNext(System.out::println)
            .count()
            .subscribe(
                number -> System.out.println("Heroes have " + number + " unique super powers")
            );
    }

}
