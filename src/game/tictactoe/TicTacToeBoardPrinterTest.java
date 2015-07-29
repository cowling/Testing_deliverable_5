package game.tictactoe;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.inOrder;



public class TicTacToeBoardPrinterTest {
	
	private TicTacToeBoardPrinter printer;

	@Mock
	private PrintStream printStream;

	@Before
	  public void setup() {
		MockitoAnnotations.initMocks(this);
	    printer = new TicTacToeBoardPrinter(printStream);
	  }
	
	@Test
	public void testPrintGameBoard() {
		GameBoard board = new GameBoard();
		printer.printGameBoard(board);
		InOrder inOrder = inOrder(printStream);
	    inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
	    inOrder.verify(printStream).println("-+-+-");
	    inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
	    inOrder.verify(printStream).println("-+-+-");
	    inOrder.verify(printStream).printf("%s|%s|%s\n", " ", " ", " ");
		
	}

}


