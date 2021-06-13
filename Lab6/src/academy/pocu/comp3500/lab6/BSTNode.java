package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class BSTNode {
    private Player player;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public BSTNode getLeft() {
        return this.left;
    }

    public BSTNode getRight() {
        return this.right;
    }

    public static BSTNode getMatchingPlayerOrNull(BSTNode node, Player player, BSTNode bestMatchingPlayerNode) {
        if (node == null) {
            return bestMatchingPlayerNode;
        }

        if (node.player == player) {
            var leftMatchingPlayerNode = getMatchingPlayerOrNull(node.left, player, bestMatchingPlayerNode);
            var rightMatchingPlayerNode = getMatchingPlayerOrNull(node.right, player, bestMatchingPlayerNode);

            if (player.getRating() == leftMatchingPlayerNode.player.getRating())
                return leftMatchingPlayerNode;
            if (player.getRating() == rightMatchingPlayerNode.player.getRating())
                return rightMatchingPlayerNode;

            var ratingGapFromLeftPlayerNode = Math.abs(leftMatchingPlayerNode.player.getRating() - player.getRating());
            var ratingGapFromRightPlayerNode = Math.abs(rightMatchingPlayerNode.player.getRating() - player.getRating());

            if (ratingGapFromLeftPlayerNode < ratingGapFromRightPlayerNode) {
                return leftMatchingPlayerNode;
            } else {
                return rightMatchingPlayerNode;
            }
        } else {
            if (player.getRating() == node.player.getRating()) {
                return node;
            } else {
                if (bestMatchingPlayerNode == null) {
                    bestMatchingPlayerNode = node;
                } else {
                    var ratingGapFromCurrentNode = Math.abs(player.getRating() - node.player.getRating());
                    var ratingGapFromBeforeNode = Math.abs(player.getRating() - bestMatchingPlayerNode.player.getRating());
                    if (ratingGapFromCurrentNode < ratingGapFromBeforeNode) {
                        bestMatchingPlayerNode = node;
                    } else if (ratingGapFromCurrentNode == ratingGapFromBeforeNode) {
                        if (node.player.getRating() > bestMatchingPlayerNode.player.getRating()) {
                            bestMatchingPlayerNode = node;
                        }
                    }
                }
            }

            if (player.getRating() < node.player.getRating()) {
                return getMatchingPlayerOrNull(node.left, player, bestMatchingPlayerNode);
            }
            return getMatchingPlayerOrNull(node.right, player, bestMatchingPlayerNode);
        }
    }

    public static int getTopRecursive(BSTNode node, final Player[] result, int cursor, int size) {
        if (node == null)
            return cursor;

        if (node.right != null) {
            cursor = getTopRecursive(node.right, result, cursor, size);
        }

        if (cursor == size)
            return cursor;

        result[cursor] = node.player;
        cursor++;

        if (cursor == size)
            return cursor;

        if (node.left != null) {
            cursor = getTopRecursive(node.left, result, cursor, size);
        }

        return cursor;
    }

    public static int getBottomRecursive(BSTNode node, final Player[] result, int cursor, int size) {
        if (node == null)
            return cursor;

        if (node.left != null) {
            cursor = getBottomRecursive(node.left, result, cursor, size);
        }

        if (cursor == size)
            return cursor;

        result[cursor] = node.player;
        cursor++;

        if (cursor == size)
            return cursor;

        if (node.right != null) {
            cursor = getBottomRecursive(node.right, result, cursor, size);
        }

        return cursor;
    }

    public static BSTNode getNodeOrNull(BSTNode node, Player player) {
        if (node == null) {
            return null;
        }
        if (node.player == player) {
            return node;
        }
        if (player.getRating() < node.player.getRating()) {
            return getNodeOrNull(node.left, player);
        }
        return getNodeOrNull(node.right, player);
    }

    public static BSTNode insertRecursive(final BSTNode node, Player player) {
        if (node == null) {
            return new BSTNode(player);
        }

        if (player.getRating() < node.player.getRating()) {
            node.left = insertRecursive(node.left, player);
        } else {
            node.right = insertRecursive(node.right, player);
        }

        return node;
    }

    public static BSTNode joinRecursive(final BSTNode node, Player player, Result success) {
        if (node == null) {
            success.isSuccess = true;
            return new BSTNode(player);
        }

        if (node.player.getId() == player.getId()) {
            success.isSuccess = false;
            return node;
        }

        if (player.getRating() < node.player.getRating()) {
            node.left = joinRecursive(node.left, player, success);
        } else {
            node.right = joinRecursive(node.right, player, success);
        }

        return node;
    }

    public static BSTNode deleteRecursive(BSTNode node, Player player, Result deleteSuccess) {
        if (node == null)
        {
            deleteSuccess.isSuccess = false;
            return null;
        }
        if (player.getRating() > node.player.getRating()) {
            node.right = deleteRecursive(node.right, player, deleteSuccess);
        } else if (player.getRating() < node.player.getRating()) {
            node.left = deleteRecursive(node.left, player, deleteSuccess);
        } else {
            if (node.player.getId() != player.getId()) {
                node.right = deleteRecursive(node.right, player, deleteSuccess);
            } else {
                if (node.left == null && node.right == null) {
                    deleteSuccess.isSuccess = true;
                    node = null;
                } else if (node.right != null) {
                    node.player = successor(node);
                    node.right = deleteRecursive(node.right, node.player, deleteSuccess);
                } else {
                    node.player = predecessor(node);
                    node.left = deleteRecursive(node.left, node.player, deleteSuccess);
                }
            }
        }
        return node;
    }

    private static Player successor(BSTNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.player;
    }

    private static Player predecessor(BSTNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.player;
    }
}
