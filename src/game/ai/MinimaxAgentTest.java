package game.ai;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static game.tictactoe.TicTacToeGameState.Player.O;
import static game.tictactoe.TicTacToeGameState.Player.X;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import game.DiscreteGameState;
import game.ai.heuristic.StateEvaluator;
import game.tictactoe.GameBoard;
import game.tictactoe.TicTacToeGameState;
import game.tictactoe.TicTacToeGameState.Player;

public class MinimaxAgentTest {

	private StateEvaluator evaluator = mock(StateEvaluator.class);
	private MinimaxAgent<TicTacToeGameState> minimaxAgent = new MinimaxAgent<TicTacToeGameState>(evaluator);
	private TicTacToeGameState currentState = mock(TicTacToeGameState.class);
	public ExpectedException thrown = ExpectedException.none();

	//test eveluateNextState(T currentState)
	//return evaluateNextState(currentState, Integer.MAX_VALUE), which is null.
	@Test
	public void testEvaluateNextStateByCurrentState() {
		DiscreteGameState next = minimaxAgent.evaluateNextState(currentState, Integer.MAX_VALUE);
		assertNull(next);

	}

	//test eveluateNextState(T currentState, int depth)
	//if(currentSate == null), it will throw an IllegalArgumentException("initialState cannot be null").
	@Test
	public void testEvaluateNextStateByCurrentStateNull() {
		try {
			minimaxAgent.evaluateNextState(null, 1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException anIllegalArgumentException) {
			thrown.expect(IllegalArgumentException.class);
			assertEquals("initialState cannot be null", anIllegalArgumentException.getMessage());
		}
	}

	//test eveluateNextState(T currentState, int depth)
	//set depth = -1
	//if(depth < 0), it will throw an IllegalArgumentException("depth cannot be less than zero. depth=-1").
	@Test
	public void testEvaluateNextStateByDepthNegative() {
		try {
			minimaxAgent.evaluateNextState(currentState, -1);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException anIllegalArgumentException) {
			thrown.expect(IllegalArgumentException.class);
			assertEquals("depth cannot be less than zero. depth=-1", anIllegalArgumentException.getMessage());
		}
	}

	//test to evaluate the next state, when currentState != null and depth > 0.
	@Test
	public void testEvaluateNextStateInProcess() {
		TicTacToeGameState curState = new TicTacToeGameState(new GameBoard(new Player[][] {{X, null, null}, {X, O, null},
				{null, null, null}}), O);
		TicTacToeGameState state = minimaxAgent.evaluateNextState(curState);
		assertFalse(state.hasWin(O));
	}

	//test: evaluate the next state, when currentState != null and depth > 0.
	//And then the player will win.
	@Test
	public void testEvaluateNextStateToWin() {
		TicTacToeGameState curState = new TicTacToeGameState(new GameBoard(new Player[][] {{O, O, X}, {O, O, X},
				{X, X, null}}), O);
		TicTacToeGameState state = minimaxAgent.evaluateNextState(curState);
		assertTrue(state.hasWin(O));
	}

	//test: eveluate the next state, when currentState is over.
	//it will return null.
	@Test
	public void testEvaluateStateOver() {
		when(currentState.isOver()).thenReturn(true);
		assertNull(minimaxAgent.evaluateNextState(currentState));
	}
}
