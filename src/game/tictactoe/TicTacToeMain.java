package game.tictactoe;

import java.util.Scanner;

import game.ai.GameIntelligenceAgent;
import game.ai.MinimaxAgent;
import game.ai.heuristic.tictactoe.TicTacToeEvaluator;
import game.tictactoe.TicTacToeGameState.Player;

public class TicTacToeMain {

  /**
   * @param args
   */
  public static void main(String[] args) {
    TicTacToeEvaluator evaluator = new TicTacToeEvaluator(Player.O);
    GameIntelligenceAgent<TicTacToeGameState> agent =
        new MinimaxAgent<TicTacToeGameState>(evaluator);
    Scanner scanner = new Scanner(System.in);
    TicTacToeGameRunner game = new TicTacToeGameRunner(agent, scanner, System.out);

    game.run();
  }

}
