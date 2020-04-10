package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

public class Code3 {

    public static void main(String... args) {
        Observable.just("Black Canary", "Catwoman", "Elektra")
                .map(name -> {
                    if (name.contains("tra")) {
                        throw new RuntimeException("The error just happend!");
                    } else {
                        return name;
                    }
                })
                // Use the `subscribe` method using the 3 parameters
                // to receive (and print):
                // 1. the item
                // 2. the error
                // 3. the completion event
                .subscribe(
                        item -> System.out.println(item),
                        Throwable::printStackTrace,
                        () -> System.out.println("Completion"))
        ;
    }
}
