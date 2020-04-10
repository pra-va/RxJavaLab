package me.escoffier.lab.chapter4;


import io.vertx.core.json.JsonObject;
import me.escoffier.superheroes.Character;
import org.junit.Test;

import java.util.Arrays;

import static me.escoffier.lab.chapter4.Code13.fight;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class Code13Test {

    public final String winner = "{\n" +
            "  \"hero\" : \"Looser Character\",\n" +
            "  \"villain\" : \"Winner Character\",\n" +
            "  \"winner\" : \"Winner Character\"\n" +
            "}";
    public final String noneWinner = "{\n" +
            "  \"hero\" : null,\n" +
            "  \"villain\" : null,\n" +
            "  \"winner\" : \"none\"\n" +
            "}";

    @Test
    public void fightShouldBeWonByCharacterWithMoreSuperPowers() throws NoSuchMethodException {
        Character firstCharacter = mock(Character.class);
        Character secondCharacter = mock(Character.class);

        when(firstCharacter.getSuperpowers()).thenReturn(Arrays.asList("power1", "power2"));
        when(secondCharacter.getSuperpowers()).thenReturn(Arrays.asList("power1", "power2", "power3"));

        when(firstCharacter.getName()).thenReturn("Looser Character");
        when(secondCharacter.getName()).thenReturn("Winner Character");

        JsonObject winner = fight(firstCharacter, secondCharacter);

        assertEquals(this.winner, winner.encodePrettily());
    }

    @Test
    public void fightShouldHaveNoWinnerIfSuperPowersListsHaveSameSize() {
        Character firstCharacter = mock(Character.class);
        Character secondCharacter = mock(Character.class);

        when(firstCharacter.getSuperpowers()).thenReturn(Arrays.asList("power1", "power2"));
        when(secondCharacter.getSuperpowers()).thenReturn(Arrays.asList("power2", "power3"));

        JsonObject winner = fight(firstCharacter, secondCharacter);

        assertEquals(this.noneWinner, winner.encodePrettily());
    }

    @Test
    public void fightShouldBeInvokedOnlyOnce() {
        Code13 mock = mock(Code13.class);
        Character firstCharacter = mock(Character.class);
        Character secondCharacter = mock(Character.class);
        Character thirdCharacter = mock(Character.class);

        verifyNoInteractions(mock);

        mock.fight(firstCharacter, secondCharacter);

        verify(mock, times(222)).fight(firstCharacter, thirdCharacter);

    }

}