package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;


public class League {
    private BSTNode bstRoot;
    private int playerCount = 0;

    public League() {

    }

    public League(Player[] players, boolean isSorted) {
        if (players == null) {
            return;
        }

        this.playerCount = players.length;
        if (isSorted) {
            bstRoot = BSTNode.insertSortedRecursive(bstRoot, players, 0, playerCount - 1);
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
        Player[] result;
        if (playerCount < count) {
            result = new Player[playerCount];
        } else {
            result = new Player[count];
        }
        Integer cursor = 0;
        BSTNode.getBottomRecursive(bstRoot, result, cursor, count);

        return result;
    }

    public boolean join(final Player player) {
        if (player == null) {
            return false;
        }

        Result success = new Result();
        bstRoot = BSTNode.joinRecursive(bstRoot, player, success);
        if (success.isSuccess)
            playerCount++;

        return success.isSuccess;
    }

    public boolean leave(final Player player) {
        if (player == null) {
            return false;
        }

        Result deleteSuccess = new Result();
        bstRoot = BSTNode.deleteRecursive(bstRoot, player, deleteSuccess);
        if (deleteSuccess.isSuccess) {
            playerCount--;
        }
        return deleteSuccess.isSuccess;
    }
}