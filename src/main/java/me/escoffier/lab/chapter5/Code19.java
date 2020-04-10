package me.escoffier.lab.chapter5;

import io.reactivex.Single;
import me.escoffier.superheroes.Character;

import java.util.Arrays;

public class Code19 {

    public static void main(String[] args) {
        System.out.println("Before operation");
        Character result = getBlockingSuperVillain().blockingGet();
        System.out.println("After operation: " + result);
    }

    private static Single<Character> getBlockingSuperVillain() {
        return Single.create(emitter ->
                new Thread(() -> {
                    System.out.println("Operation starting");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        emitter.onError(e);
                    }
                    emitter.onSuccess(new Character("Fart-man", Arrays.asList("Silent fart", "Long fart"),
                            true));
                }).start()
        );
    }

}
