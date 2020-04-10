package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import io.reactivex.Observable;

import static me.escoffier.superheroes.Helpers.villains_names;

public class Code2 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        int index = 0;
//         Extract 10 villains that are in the position 21 -> 31 in the stream
        villains_names()
            .skip(20)
                .take(10)
            .subscribe(System.out::println);
    }

}
