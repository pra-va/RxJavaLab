package me.escoffier.lab.chapter5;

import io.reactivex.observers.TestObserver;
import io.vertx.core.json.JsonArray;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static me.escoffier.lab.chapter5.Code2.load;

public class Code2Test {

    @Test
    public void loadShouldLoadFileCorrectly() throws InterruptedException {
        TestObserver<JsonArray> heroesList = load().test();

        heroesList.await(300, TimeUnit.MILLISECONDS);

        heroesList.assertNoErrors()
                .assertValueCount(1)
                .assertValue(array -> array.size() == 727);
    }
}