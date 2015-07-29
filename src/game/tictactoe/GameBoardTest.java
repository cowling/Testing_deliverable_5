package game.tictactoe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import game.Position;
import game.tictactoe.TicTacToeGameState.Player;

public class GameBoardTest {
	
	private GameBoard board = new GameBoard();
	public ExpectedException thrown = ExpectedException.none();
	
	
	//test mark(int row, int col, Player player)
	//set row=-1 is not included in the boundary
	@Test
	public void testMarkRowColOffBoard1() {
		try {
	    	board.mark(-1, 0, Player.X);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
        	thrown.expect(IllegalArgumentException.class);
            assertEquals("(" + -1 + "," + 0 + ") is off the board", anIllegalArgumentException.getMessage());
        }
	}
	
	//test mark(int row, int col, Player player)
		//set row=3 is not included in the boundary
		@Test
		public void testMarkRowColOffBoard2() {
			try {
		    	board.mark(3, 0, Player.X);
	            fail("Expected an IllegalArgumentException to be thrown");
	        } catch (IllegalArgumentException anIllegalArgumentException) {
	        	thrown.expect(IllegalArgumentException.class);
	            assertEquals("(" + 3 + "," + 0 + ") is off the board", anIllegalArgumentException.getMessage());
	        }
		}
	

	//test mark(int row, int col, Player player)
	//if player==null, it will throw an IllegalArgumentException("cannot mark null player").
	@Test
	public void testMarkPlayerNull() {
		try {
	    	board.mark(0, 0, null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
        	thrown.expect(IllegalArgumentException.class);
            assertEquals("cannot mark null player", anIllegalArgumentException.getMessage());
        }
	}
	
	//test mark(int row, int col, Player player)
	//if board[row][col] is not null, then it will return false.
	@Test
	public void testMarkFail() {
		board.mark(0, 0, Player.O);
		Boolean mark = board.mark(0, 0, Player.X);
		assertFalse(mark);
	}
		
	//test mark(int row, int col, Player player)
    //if board[row][col] is null, then board[row][col]=player and it will return true.
	@Test
	public void testMarkSucceed() {
		Boolean mark = board.mark(1, 1, Player.X);
		assertTrue(mark);
	}
	
	//test getMark(int row, int col)
	//set col=-1 is not included in the boundary
		@Test
		public void testGetMarkRowColOffBoard1() {
			try {
		    	board.getMark(0, -1);
	            fail("Expected an IllegalArgumentException to be thrown");
	        } catch (IllegalArgumentException anIllegalArgumentException) {
	        	thrown.expect(IllegalArgumentException.class);
	            assertEquals("(" + 0 + "," + -1 + ") is off the board", anIllegalArgumentException.getMessage());
	        }
		}
		
		//test getMark(int row, int col)
		//set col=3 is not included in the boundary
			@Test
			public void testGetMarkRowColOffBoard2() {
				try {
			    	board.getMark(0, 3);
		            fail("Expected an IllegalArgumentException to be thrown");
		        } catch (IllegalArgumentException anIllegalArgumentException) {
		        	thrown.expect(IllegalArgumentException.class);
		            assertEquals("(" + 0 + "," + 3 + ") is off the board", anIllegalArgumentException.getMessage());
		        }
			}
		
		//test getMark(int row, int col)
		//After set board[row][col], it could get marked index by this method.
		@Test
		public void testGetMark() {
			board.mark(2, 2, Player.O);
			assertEquals(Player.O, board.getMark(2, 2));
		}
		
		//test getOpenPositions()
		//After marking, the length of positions decrease.
		@Test
		public void testGetOpenPositions() {
			int size1 = board.getOpenPositions().size();
			assert(board.getOpenPositions().contains(new ArrayList<Position>().addAll(Arrays.asList(new Position(0, 0), new Position(0, 1),
			        new Position(0, 2), new Position(1, 0), new Position(1, 1), new Position(1, 2),
			        new Position(2, 0), new Position(2, 1), new Position(2, 2)))));
			board.mark(2, 0, Player.X);
			assertEquals(size1-1, board.getOpenPositions().size());
			assertFalse(board.getOpenPositions().contains(new Position(2, 0)));
		}

		//test toString()
		@Test
		public void testToString() {
			board.mark(0, 0, Player.O);
			board.mark(1, 0, Player.X);
			assertEquals("O  \nX  \n   \n", board.toString());			
		}
		
		//test equals(Object ogj)
		@Test
		public void testEquals() {
			//
			GameBoard other1 = new GameBoard();
			assertTrue(board.equals(other1));
			//
			Position p = new Position(0, 0);
			assertFalse(board.equals(p));
			//
			Player[][] players = new Player[1][1];
			GameBoard other2 = new GameBoard(players);
			assertFalse(board.equals(other2));			
		}
		
		//test hashCode()
		//the same input hashes to the same number
		@Test
		public void testHashCode() {
			assertEquals(29612254, board.hashCode());
		}
}
