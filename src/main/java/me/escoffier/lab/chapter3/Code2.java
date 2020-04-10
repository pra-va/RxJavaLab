package me.escoffier.lab.chapter3;


import io.reactivex.Observable;
import io.reactivex.Single;

public class Code2 {


    public static void main(String[] args) {
        Single.just("Superman")
            // use subscribe to retrieve the element and the error if any.
            .subscribe((item, error) -> {
                if (error == null) {
                    System.out.println("Hello, " + item);
                } else {
                    System.out.println("Error with message: " + error.getMessage());
                }
            })
        ;
    }
}
