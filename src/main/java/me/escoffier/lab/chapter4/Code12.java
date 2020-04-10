package me.escoffier.lab.chapter4;


import io.reactivex.Flowable;
import me.escoffier.superheroes.Character;

import static me.escoffier.superheroes.Helpers.*;

public class Code12 {

    public static void main(String[] args) {
        Flowable<String> villains_superpowers =
            villains().map(Character::getSuperpowers)
                .flatMap(Flowable::fromIterable);
        Flowable<String> heroes_superpowers =
            heroes().map(Character::getSuperpowers)
                .flatMap(Flowable::fromIterable);

        villains_superpowers
        // Merge both stream using the `mergeWith` operator
                .mergeWith(heroes_superpowers)

        // Filter out duplicates using the `distinct` operator
                .distinct()

        // Count the number of item using the count operator
                .count()

        // Subscribe to print the number of unique super powers
                .subscribe(count -> System.out.println("There are " + count + " distinct super powers."))
        ;
    }
}
