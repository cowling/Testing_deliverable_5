package game.ai.heuristic.tictactoe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;
import game.DiscreteGameState;
import game.tictactoe.TicTacToeGameState;
import game.tictactoe.TicTacToeGameState.Player;

public class TicTacToeEvaluatorTest {
	
	 private TicTacToeGameState game = mock(TicTacToeGameState.class);
	 private TicTacToeEvaluator evaluator = new TicTacToeEvaluator(Player.X);
	 public ExpectedException thrown = ExpectedException.none();
	 private List<DiscreteGameState> listAvailableStates = new ArrayList<DiscreteGameState>();
	 private DiscreteGameState availableState = mock(DiscreteGameState.class);

	 
	 //test if(game == null)
	 //then it will throw an IllegalArgumentException and show "cannot evaluate null game".	 
	@Test
	public void testEvaluatorGameNull() {	
	    try {
	    	evaluator.evaluate(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
        	thrown.expect(IllegalArgumentException.class);
            assertEquals("cannot evaluate null game", anIllegalArgumentException.getMessage());
        }
	}
	
	//test if(game.hasWin(player))
	//if it is true, then game.availableStates().size() will add 1.
	@Test
	public void testEvaluatorWinPlayer() {
		listAvailableStates.add(availableState);
		 when(game.hasWin(Player.X)).thenReturn(true);
		 when(game.availableStates()).thenReturn(listAvailableStates);
		 assertEquals(2, evaluator.evaluate(game));		 
	}
	
    //test if(game.hasWin(opponentOf(player)))
	//if it is true, then return -1.
	@Test
	public void testEvaluatorWinOpponent() {
		when(game.hasWin(Player.O)).thenReturn(true);
		assertEquals(-1, evaluator.evaluate(game));	
	}
	
	//test tie
	//if they are tie, then return 0
	@Test
	public void testEvaluatorTie() {
		when(game.hasWin(Player.X)).thenReturn(false);
		when(game.hasWin(Player.O)).thenReturn(false);
		assertEquals(0, evaluator.evaluate(game));
	}
}
