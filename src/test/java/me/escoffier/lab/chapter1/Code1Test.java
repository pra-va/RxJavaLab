package me.escoffier.lab.chapter1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

import static me.escoffier.lab.chapter1.Code1.main;

public class Code1Test {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final String consoleContent = "Superman\nBatman\nAquaman\nAsterix\nCaptain America";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOutput);
    }

    @Test
    public void mainMethodShouldReturnFullListOfHeroesToConsole() {
        main();
        assertEquals(consoleContent, outputStream.toString().trim());
    }
}