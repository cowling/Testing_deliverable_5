package game.tictactoe;

import static org.junit.Assert.*;

import org.mockito.*;

import game.Position;
import game.ai.GameIntelligenceAgent;
import game.tictactoe.TicTacToeGameState.Player;

import java.util.Scanner;
import java.io.PrintStream;

import static org.fest.util.Strings.join; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
//@RunWith(MockitoJUnitRunner.class)
public class TicTacToeGameRunnerTest {
	 String line = System.getProperty("line.separator");

	@Mock
	private GameIntelligenceAgent<TicTacToeGameState> agent;
	@Mock
	private PrintStream printStream;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	  @Test
	  public void testMoveComputer() {
	    TicTacToeGameRunner runner = new TicTacToeGameRunner(agent, new Scanner(""), printStream);
	    TicTacToeGameState state = mock(TicTacToeGameState.class);
	    when(state.getLastMove()).thenReturn(new Position(0, 0));
	    when(agent.evaluateNextState(Mockito.any(TicTacToeGameState.class))).thenReturn(state);

	    assertEquals(runner.getGame().getCurrentPlayer(), (Player.X));
	    runner.moveComputer();
	    assertEquals(runner.getGame().getCurrentPlayer(), (Player.O));
	  }
	  
	  @Test
	  public void testMoveHumanAcceptInput() {
	    Scanner scanner = scannerWithInputs("", " 1, 1", "invalid", "-1,1", "3,1", "1,2,3", "0,0");
	    TicTacToeGameRunner runner = new TicTacToeGameRunner(agent, scanner, printStream);

	    runner.moveHuman();

	    verify(printStream, times(6)).println(TicTacToeGameRunner.INSTRUCTION_TEXT);
	  }

	  @Test
	  public void testMoveHumanErrorWhenOffBoard() {
	    Scanner scanner = scannerWithInputs("-1,0", "3,3", "0,0");
	    TicTacToeGameRunner runner = new TicTacToeGameRunner(agent, scanner, printStream);
	    runner.moveHuman();

	    verify(printStream).printf("(%d,%d) is not on the board. ", -1, 0);
	    verify(printStream).printf("(%d,%d) is not on the board. ", 3, 3);
	    verify(printStream, times(2)).println(TicTacToeGameRunner.INSTRUCTION_TEXT);
	  }

	  @Test
	  public void testMoveHumanErrorWhenRepeatMove() {
	    Scanner scanner = scannerWithInputs("1,1", "1,1", "0,0");
	    TicTacToeGameRunner runner = new TicTacToeGameRunner(agent, scanner, printStream);

	    runner.moveHuman();
	    runner.moveHuman();

	    verify(printStream).printf("(%d,%d) has already been taken. ", 1, 1);
	    verify(printStream).println(TicTacToeGameRunner.INSTRUCTION_TEXT);
	  }

	  @Test
	  public void testMoveHumanSwitchesPlayers() {
	    Scanner scanner = scannerWithInputs("1,1", "0,0");
	    TicTacToeGameRunner runner = new TicTacToeGameRunner(agent, scanner, printStream);

	    assertEquals(runner.getGame().getCurrentPlayer(), (Player.X));
	    runner.moveHuman();
	    assertEquals(runner.getGame().getCurrentPlayer(), (Player.O));
	  }
	  
	  //scanner helper
	  private Scanner scannerWithInputs(String... inputs) {
		 
		    String joinedInputs = join(inputs).with("\n");
		    Scanner scanner = new Scanner(joinedInputs);
		    return scanner;
	  }

}


