package me.escoffier.lab.chapter5;

import io.reactivex.subscribers.TestSubscriber;
import me.escoffier.superheroes.Character;
import org.junit.Test;

import static org.mockito.Mockito.spy;


public class Code4Test {
    private final Code4 codeSpy = spy(Code4.class);


    @Test
    public void heroesMethodShouldReturnHeroesOnly() throws InterruptedException {
        TestSubscriber<Character> subscriber = codeSpy.heroes().test(0);

        subscriber.assertNoValues()
                .requestMore(10)
                .awaitCount(10);

        subscriber.assertNoErrors()
                .assertValueCount(10)
                .requestMore(999)
                .awaitTerminalEvent();

        subscriber
                .assertValueCount(520);


    }

    @Test
    public void villainsMethodShouldReturnVilainsOnly() throws InterruptedException {
        TestSubscriber<Character> subscriber = codeSpy.villains().test(999);
        subscriber.assertNoValues()
                    .awaitTerminalEvent();

        subscriber.assertValueCount(207);
    }
}