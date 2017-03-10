import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DiceGameTest {

    @Mock
    Printer printer;

    @Mock
    Dice d1;

    @Test
    public void testExceptionAfterThreeThrows() throws Exception{

        Dice d2 = mock(Dice.class);
        DiceGame game = new DiceGame(printer, d1, d2);
        game.roll();
        game.roll();
        game.roll();
    }

    @Test
    public void testGame() throws Exception {

        Dice d2 = mock(Dice.class); // mock object of dice

        when(d1.roll()).thenReturn(3).thenReturn(5).thenReturn(3);
        when(d2.roll()).thenReturn(2).thenReturn(2).thenReturn(2);

        DiceGame game = new DiceGame(printer, d1, d2);

        game.setPlayer("Kurt");

        int result = game.roll();
        int result2 = game.roll();
        int result3 = game.roll();

        assertThat(result, is(5));
        assertThat(result2, is(7));
        assertThat(result3, is(5));


        verify(printer, times(1)).print(anyString());
    }

    @Test
    public void testGame2() throws Exception {
        Dice d2 = mock(Dice.class);


        when(d1.roll()).thenReturn(5).thenReturn(1).thenReturn(1);
        when(d2.roll()).thenReturn(2).thenReturn(3).thenReturn(3);

        DiceGame game = new DiceGame(printer, d1, d2);

        game.setPlayer("Mustafa");

        int res1 = game.roll();
        int res2 = game.roll();
        int res3 = game.roll();

        assertThat(res1, is(7));
        assertThat(res2, is(4));
        assertThat(res3, is(4));

        verify(printer, times(1)).print("Mustafa you Won the game, with: 1");
    }

}
