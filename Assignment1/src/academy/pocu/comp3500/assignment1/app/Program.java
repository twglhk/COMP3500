package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

import java.awt.desktop.SystemEventListener;

public class Program {

    public static void main(String[] args) {
        TestG02();
        testFindDreamTeam();
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

    private static void testFindDreamTeam() {
        // POCU test
        Player[] players = new Player[]{
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 9", 42, 15, 4, 56),
                new Player("Player 8", 33, 11, 3, 72),
        };

        int TEAM_SIZE = 4;

        Player[] outPlayers = new Player[TEAM_SIZE];
        Player[] scratch = new Player[TEAM_SIZE];
        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
        assert (maxTeamwork == 171);
        Player player = getPlayerOrNull(outPlayers, "Player 5");
        assert (player != null);
        player = getPlayerOrNull(outPlayers, "Player 6");
        assert (player != null);
        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);
        player = getPlayerOrNull(outPlayers, "Player 7");
        assert (player != null);

        // my test
        TEAM_SIZE = 1;
        outPlayers = new Player[TEAM_SIZE];
        scratch = new Player[TEAM_SIZE];
        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
        assert (maxTeamwork == 85);
        player = getPlayerOrNull(outPlayers, "Player 2");
        assert (player != null);
        // my test 2
        players = new Player[]{
                new Player("Player 1", 1, 2, 8, 22),
        };
        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
        assert (maxTeamwork == 16);
        player = getPlayerOrNull(outPlayers, "Player 1");
        assert (player != null);
    }

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }
}