package me.escoffier.lab.chapter7;

import io.reactivex.subscribers.TestSubscriber;
import me.escoffier.superheroes.Helpers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class AsyncTest {

    public class ErrorBox {
        Throwable error;


        public Throwable getError() {
            return error;
        }

        public void setError(Throwable error) {
            this.error = error;
        }
    }

    @Test
    public void theRightWayToTest() throws InterruptedException {
        TestSubscriber testSubscriber = Helpers.heroes().test();
        testSubscriber.await(1000, TimeUnit.MILLISECONDS);
        testSubscriber
                .assertValueCount(520)
                .assertComplete()
                .assertNoErrors();
    }
}
