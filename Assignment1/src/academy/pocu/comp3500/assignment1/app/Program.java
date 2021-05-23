package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;

import java.util.Random;

public class Program {

    public static void main(String[] args) {

        TestG02();
        myTest();
    }

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }

    public static void TestG02() {
        // Duplicated Assist
        Player[] players = new Player[]{
                new Player("Player 1", 2, 0, 100, 78),
                new Player("Player 2", 0, 4, 40, 62),
                new Player("Player 3", 10, 4, 0, 66),
                new Player("Player 4", 3, 4, 50, 22),
                new Player("Player 5", 1, 6, 7, 12),
                new Player("Player 6", 11, 6, 24, 26),
                new Player("Player 7", 7, 6, 4, 15),
                new Player("Player 8", 8, 8, 2, 11),
                new Player("Player 9", 5, 8, 10, 5),
                new Player("Player 10", 8, 8, 5, 67)
        };
        final int TEAM_SIZE = 4;
        Player[] outPlayers = new Player[TEAM_SIZE];
        Player[] scratch = new Player[TEAM_SIZE];
        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
        assert (maxTeamwork == 124 * 4);
        assert (getPlayerOrNull(outPlayers, "Player 2") != null);
        assert (getPlayerOrNull(outPlayers, "Player 4") != null);
        assert (getPlayerOrNull(outPlayers, "Player 6") != null);
        assert (getPlayerOrNull(outPlayers, "Player 9") != null);
    }

    public static void myTest() {
        {
            Random random = new Random();
            for(int testCount = 0; testCount < 1000; ++testCount) {
                Player[] players = new Player[random.nextInt(7) + 1];
                for(int i = 0; i < players.length; ++i) {
                    players[i] = new Player(String.format("Player %d", i + 1),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30));
                }
                for(int k = 1; k < players.length; ++k) {
                    Player[] outPlayers1 = new Player[k];
                    Player[] outPlayers2 = new Player[k];
                    Player[] scratch1 = new Player[k];
                    Player[] scratch2 = new Player[k];
                    long maxTeamWork1 = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers1, scratch1);
                    long maxTeamWork2 = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers2, scratch2);
                    StringBuilder testCase = new StringBuilder("\ntestCase: \n");
                    for(Player player:players) {
                        testCase.append("new Player(").append("\"").append(player.getName()).append("\", ").
                                append(player.getPointsPerGame()).append(", ").
                                append(player.getAssistsPerGame()).append(", ").
                                append(player.getPassesPerGame()).append(", ").
                                append(player.getShootingPercentage()).append("),\n");
                    }
                    String K = "K: " + k + "\n\n";
                    StringBuilder playersStr = new StringBuilder("outPlayer1: \n");
                    for (Player item : outPlayers1) {
                        playersStr.append(item.getName()).append(",\n");
                    }
                    playersStr.append("\noutPlayer2:\n");
                    for (Player value : outPlayers2) {
                        playersStr.append(value.getName()).append(",\n");
                    }
                    String teamWorkNotEqual = "maxTeamWork1: " + maxTeamWork1 + ", " + "maxTeamWork2: " + maxTeamWork2;
                    for (Player player : outPlayers1) {
                        String str = testCase.toString() + playersStr + "\nPlayers: " + players.length + "\n" + K + teamWorkNotEqual;
                        assert maxTeamWork1 == maxTeamWork2 || (getPlayerOrNull(outPlayers2, player.getName()) != null) : str + "\ncouldn't find " + player.getName() + " in player 2\n";
                    }
                }
            }
        }
    }
}