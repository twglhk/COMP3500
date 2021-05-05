package academy.pocu.comp3500.assignment1.app;
import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public class Program {

    public static void main(String[] args) {
        GameStat[] gameStats = new GameStat[] {
                new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
        };

        Player[] players = new Player[] {
                new Player(),
                new Player(),
                new Player(),
                new Player()
        };

        PocuBasketballAssociation.processGameStats(gameStats, players);
    }
}
