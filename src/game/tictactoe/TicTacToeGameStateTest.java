package game.tictactoe;

import static org.junit.Assert.*;

import java.util.*;

import game.DiscreteGameState;
import game.Position;
import game.tictactoe.TicTacToeGameState.Player;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class TicTacToeGameStateTest {
	
	private TicTacToeGameState game;

	

	@Before
	public void setup() {
	    game = new TicTacToeGameState();
	}
	
	@Test
	public void startingPlayerIsX() {
	    assertEquals(new TicTacToeGameState().getCurrentPlayer(), (Player.X));
	}

	@Test
	public void copyConstructorDeepCopiesBoard() {
	    game.play(0, 0);
	    TicTacToeGameState copy = new TicTacToeGameState(game);
	    
	    assertEquals(copy.getLastMove(), game.getLastMove());
	    assertEquals(copy.getCurrentPlayer(), game.getCurrentPlayer());
	  }

	@Test
	  public void testAvailableStatesHasOne() {
	    TicTacToeGameState game = new TicTacToeGameState();
	    game.play(0, 0);
	    game.play(0, 1);
	    game.play(0, 2);
	    game.play(1, 0);
	    game.play(1, 1);
	    game.play(1, 2);
	    game.play(2, 0);
	    game.play(2, 1);

	    List<DiscreteGameState> states = game.availableStates();
	    assertNotNull(states);
	    TicTacToeGameState availableState = (TicTacToeGameState) states.get(0);
	    assertEquals(availableState.getCurrentPlayer(), Player.opponentOf(game.getCurrentPlayer()));
	    assertEquals(availableState.getLastMove(), new Position(2, 2));
	  }
	

	  @Test
	  public void testAvailableStatesFull() {
	    TicTacToeGameState game = new TicTacToeGameState();
	    game.play(0, 0);
	    game.play(0, 1);
	    game.play(0, 2);
	    game.play(1, 0);
	    game.play(1, 1);
	    game.play(1, 2);
	    game.play(2, 0);
	    game.play(2, 1);
	    game.play(2, 2);

	    assertTrue(game.availableStates().isEmpty());
	  }
	  

	  // -- hasWin

	  @Test
	  public void testWinRow() {
	    game.play(1, 0);
	    game.play(1, 1);
	    game.play(1, 2);
	    assertTrue(game.hasWin(Player.X));
	  }

	  @Test
	  public void testWinCol() {
	    game.play(0, 1);
	    game.play(1, 1);
	    game.play(2, 1);
	    assertTrue(game.hasWin(Player.X));
	  }

	  @Test
	  public void testWinDiagonal() {
	    game.play(0, 0);
	    game.play(1, 1);
	    game.play(2, 2);
	    assertTrue(game.hasWin(Player.X));
	  }
	  
	// -- assert draw

	  @Test
	  public void testOverDraw() {
	    // OXO
	    // OXX
	    // XOO
	    game.play(0, 0);
	    game.play(0, 2);
	    game.play(1, 0);
	    game.play(2, 1);
	    game.play(2, 2);
	    game.switchPlayer();
	    game.play(0, 1);
	    game.play(1, 1);
	    game.play(1, 2);
	    game.play(2, 0);

	    assertTrue(game.isOver());
	  }
	  
	// -- play

	  @Test
	  public void testPlay() {
	    assertTrue(game.play(0, 0));
	    assertEquals(game.getLastMove(), new Position(0, 0));
	  }

	  @Test(expected=IllegalArgumentException.class)
	  public void testPlayOffBoard() {
	    game.play(-1, 0);
	  }

	  @Test
	  public void testPlaySameLocation() {
	    assertTrue(game.play(0, 0));
	    assertTrue(game.play(0, 1));
	    // should not affect the last move
	    assertFalse(game.play(0, 0));
	    assertEquals(game.getLastMove(), new Position(0, 1));
	  }

	  // -- switchPlayer

	  @Test
	  public void testSwitchPlayer() {
	    assertEquals(game.getCurrentPlayer(), Player.X);
	    game.switchPlayer();
	    assertEquals(game.getCurrentPlayer(), Player.O);
	    game.switchPlayer();
	    assertEquals(game.getCurrentPlayer(), Player.X);
	  }
}
