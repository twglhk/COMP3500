package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.Arrays;

public class League {
    private BSTNode bstRoot;
    private int playerCount = 0;

    public League() {
        bstRoot = new BSTNode(null);
    }

    public League(Player[] players, boolean isSorted) {
        if (players == null) {
            return;
        }

        this.playerCount = players.length;
        if (isSorted) {
            bstRoot = new BSTNode(players[players.length / 2]);
            for (int i = 0; i < players.length / 2; ++i) {
                BSTNode.insertRecursive(bstRoot, players[i]);
            }
            for (int i = (players.length / 2) + 1; i < players.length; ++i) {
                BSTNode.insertRecursive(bstRoot, players[i]);
            }
        } else {
            bstRoot = new BSTNode(players[0]);
            for (int i = 1; i < players.length; ++i) {
                BSTNode.insertRecursive(bstRoot, players[i]);
            }
        }
    }

    public Player findMatchOrNull(final Player player) {
        BSTNode matchingPlayerNode = BSTNode.getMatchingPlayerOrNull(bstRoot, player, null);
        if (matchingPlayerNode == null)
            return null;
        else
            return matchingPlayerNode.getPlayer();
    }

    public Player[] getTop(final int count) {
        if (count == 0)
            return null;

        Player[] result;
        Integer cursor = 0;

        if (count < playerCount) {
            result = new Player[count];
            BSTNode.getTopRecursive(bstRoot, result, cursor, count);
        } else {
            result = new Player[playerCount];
            BSTNode.getTopRecursive(bstRoot, result, cursor, playerCount);
        }

        return result;
    }

    public Player[] getBottom(final int count) {
        if (count == 0)
            return null;

        var result = new Player[count];
        Integer cursor = 0;
        BSTNode.getBottomRecursive(bstRoot, result, cursor, count);

        return result;
    }

    public boolean join(final Player player) {
        Result success = new Result();
        var result = BSTNode.joinRecursive(bstRoot, player, success);
        return success.isSuccess;
    }

    public boolean leave(final Player player) {
        Result deleteSuccess = new Result();
        BSTNode.deleteRecursive(bstRoot, player, deleteSuccess);
        return deleteSuccess.isSuccess;
    }
}