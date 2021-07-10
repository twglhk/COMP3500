package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.Move;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.util.*;

public class Player extends PlayerBase {
    private int miniMaxDepth = 1;
    private final static int BOARD_SIZE = 8;
    private ArrayList<Piece> whitePieceList;
    private ArrayList<Piece> blackPieceList;
    private HashMap<Character, Integer> piecePointHashMap;
    private static final int[][] kingMoveOffsets = {
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, 1},
            {0, -1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    private static final int[][] knightMoveOffsets = {
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}
    };


    public Player(boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
        whitePieceList = new ArrayList<Piece>();
        blackPieceList = new ArrayList<Piece>();
        piecePointHashMap = new HashMap<Character, Integer>();

        // White pieces
        int y = BOARD_SIZE - 1;
        whitePieceList.add(new Piece('r', 0, y));
        whitePieceList.add(new Piece('n', 1, y));
        whitePieceList.add(new Piece('b', 2, y));
        whitePieceList.add(new Piece('k', 3, y));
        whitePieceList.add(new Piece('q', 4, y));
        whitePieceList.add(new Piece('b', 5, y));
        whitePieceList.add(new Piece('n', 6, y));
        whitePieceList.add(new Piece('r', 7, y));

        // White pawns
        y -= 1;
        for (int x = 0; x < BOARD_SIZE; ++x) {
            whitePieceList.add(new Piece('p', x, y));
        }

        // Black pawns
        y = 1;
        for (int x = 0; x < BOARD_SIZE; ++x) {
            blackPieceList.add(new Piece('P', x, y));
        }

        // Black pieces
        y = 0;
        blackPieceList.add(new Piece('R', 0, y));
        blackPieceList.add(new Piece('N', 1, y));
        blackPieceList.add(new Piece('B', 2, y));
        blackPieceList.add(new Piece('K', 3, y));
        blackPieceList.add(new Piece('Q', 4, y));
        blackPieceList.add(new Piece('B', 5, y));
        blackPieceList.add(new Piece('N', 6, y));
        blackPieceList.add(new Piece('R', 7, y));

        piecePointHashMap.put('K', 900);
        piecePointHashMap.put('Q', 90);
        piecePointHashMap.put('R', 50);
        piecePointHashMap.put('B', 30);
        piecePointHashMap.put('N', 30);
        piecePointHashMap.put('P', 10);
    }

    @Override
    public Move getNextMove(char[][] board) {
        Move nextMove = getBestMoveRecursive(board, 0, isWhite()).move;
        moveUpdate(nextMove, isWhite());
        //printPieceInfo();
        return nextMove;
    }

    @Override
    public Move getNextMove(char[][] board, Move opponentMove) {
        moveUpdate(opponentMove, !isWhite());
        //printPieceInfo();
        Move nextMove = getBestMoveRecursive(board, 0, isWhite()).move;
        moveUpdate(nextMove, isWhite());
        //printPieceInfo();
        return nextMove;
    }

    private void moveUpdate(Move move, boolean isWhite) {
        if (isWhite) {
            for (int i = 0; i < whitePieceList.size(); ++i) {
                if (whitePieceList.get(i).xPos == move.fromX && whitePieceList.get(i).yPos == move.fromY) {
                    whitePieceList.get(i).xPos = move.toX;
                    whitePieceList.get(i).yPos = move.toY;
                    break;
                }
            }
            for (int i = 0; i < blackPieceList.size(); ++i) {
                if (blackPieceList.get(i).xPos == move.toX && blackPieceList.get(i).yPos == move.toY) {
                    blackPieceList.remove(i);
                }
            }
        } else {
            for (int i = 0; i < blackPieceList.size(); ++i) {
                if (blackPieceList.get(i).xPos == move.fromX && blackPieceList.get(i).yPos == move.fromY) {
                    blackPieceList.get(i).xPos = move.toX;
                    blackPieceList.get(i).yPos = move.toY;
                    break;
                }
            }
            for (int i = 0; i < whitePieceList.size(); ++i) {
                if (whitePieceList.get(i).xPos == move.toX && whitePieceList.get(i).yPos == move.toY) {
                    whitePieceList.remove(i);
                }
            }
        }
    }

    private void printPieceInfo() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < whitePieceList.size(); ++i) {
            board[whitePieceList.get(i).yPos][whitePieceList.get(i).xPos] = whitePieceList.get(i).pieceName;
        }
        for (int i = 0; i < blackPieceList.size(); ++i) {
            board[blackPieceList.get(i).yPos][blackPieceList.get(i).xPos] = blackPieceList.get(i).pieceName;
        }

        for (int y = 0; y < BOARD_SIZE; ++y) {
            for (int x = 0; x < BOARD_SIZE; ++x) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    private Evaluation getBestMoveRecursive(char[][] board, int currentDepth, boolean isWhite) {
        // 깊이에 도달했을 경우 마감
        if (currentDepth == miniMaxDepth)
            return new Evaluation(0, null);

        // 이번에 움직일 수 있는 말 리스트 찾기
        ArrayList<Piece> movablePieceList;
        if (isWhite) {
            movablePieceList = whitePieceList;
        } else {
            movablePieceList = blackPieceList;
        }

        // 보드 순회하면서 각 말이 해당 지점에 올 수 있는지 확인하고 그 말이 해당 지점에 왔을 때 점수 체크하고 저장
        var evaluationList = new ArrayList<Evaluation>();
        var tempMove = new Move();
        for (int y = 0; y < BOARD_SIZE; ++y) {
            for (int x = 0; x < BOARD_SIZE; ++x) {
                Piece bestMovablePiece = null;
                for (var movablePiece : movablePieceList) {
                    // 기물마다 해당 좌표 이동 가능 체크
                    tempMove.fromX = movablePiece.xPos;
                    tempMove.fromY = movablePiece.yPos;
                    tempMove.toX = x;
                    tempMove.toY = y;
//                    System.out.println("Depth : " + currentDepth + ", 움직일 말 : " + movablePiece.pieceName + "["+tempMove.fromY+"]["+ tempMove.fromY + "]" + " => " + "["+tempMove.toY+"]["+ tempMove.toX + "]");

                    if (!isMoveValid(board, movablePiece, tempMove)) continue;

                    // 기존 이동 후보 기물의 움직임과 비교해 비교적 중요도가 낮은 애가 이동할 것이라 예측하고 갱신
                    if (bestMovablePiece == null) {
                        bestMovablePiece = movablePiece;
                    } else {
                        if (bestMovablePiece.importance > movablePiece.importance) {
                            bestMovablePiece = movablePiece;
                        }
                    }
                }

                // 해당 위치로 움직일 수 있는 기물이 없으면 스킵
                if (bestMovablePiece == null) continue;

                // 다음 재귀를 위한 보드 복사
                var newBoard = copyBoard(board);

                // 잡은 상대 말이 있다면 다음 깊이로 진행하기 이전에 제거 처리 후 점수 기록
                Piece caughtPiece = null;
                int evaluatedScore = 0;
                if (newBoard[y][x] != 0) {
                    if (isWhite) {
                        for (int i = 0; i < blackPieceList.size(); ++i) {
                            if (blackPieceList.get(i).pieceName == newBoard[y][x]) {
                                evaluatedScore += piecePointHashMap.get(Character.toUpperCase(blackPieceList.get(i).pieceName));
                                caughtPiece = blackPieceList.get(i);
                                blackPieceList.remove(i);
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < whitePieceList.size(); ++i) {
                            if (whitePieceList.get(i).pieceName == newBoard[y][x]) {
                                evaluatedScore -= piecePointHashMap.get(Character.toUpperCase(whitePieceList.get(i).pieceName));
                                caughtPiece = whitePieceList.get(i);
                                whitePieceList.remove(i);
                                break;
                            }
                        }
                    }
                }

                // 이동 실행. 이동하기 이전 위치 저장
                var fromMovablePieceXPos = bestMovablePiece.xPos;
                var fromMovablePieceYPos = bestMovablePiece.yPos;
                newBoard[fromMovablePieceYPos][fromMovablePieceXPos] = 0;
                newBoard[y][x] = bestMovablePiece.pieceName;
                bestMovablePiece.xPos = x;
                bestMovablePiece.yPos = y;

                // 다음 깊이 재귀하면서 해당 트리의 점수 겟
                var totalScore = getBestMoveRecursive(newBoard, currentDepth + 1, !isWhite).score + evaluatedScore;

                // 평가 저장
                var evaluation = new Evaluation(totalScore, new Move(fromMovablePieceXPos, fromMovablePieceYPos, x, y));
                evaluationList.add(evaluation);

                // 잡았던 말은 다음 탐색을 위해 복구
                if (caughtPiece != null) {
                    if (isWhite) {
                        blackPieceList.add(caughtPiece);
                    } else {
                        whitePieceList.add(caughtPiece);
                    }
                }

                // 이동시켰던 말의 위치 복구
                bestMovablePiece.xPos = fromMovablePieceXPos;
                bestMovablePiece.yPos = fromMovablePieceYPos;
            }
        }

        // 기록된 점수 중 베스트 점수를 기록한 Move 리턴
        var resultEvaluation = evaluationList.get(0);
        for (int i = 1; i < evaluationList.size(); ++i) {
            if (isWhite) {
                if (resultEvaluation.score < evaluationList.get(i).score)
                    resultEvaluation = evaluationList.get(i);
            } else {
                if (resultEvaluation.score > evaluationList.get(i).score)
                    resultEvaluation = evaluationList.get(i);
            }
        }

        return resultEvaluation;
    }

    private boolean isMyPiece(char piece) {
        if (isWhite())
            return Character.isLowerCase(piece);
        else
            return Character.isUpperCase(piece);
    }

    private boolean isOpponentPiece(char piece) {
        if (isWhite())
            return Character.isUpperCase(piece);
        else
            return Character.isLowerCase(piece);
    }

    private static char[][] copyBoard(final char[][] board) {
        char[][] newBoard = new char[BOARD_SIZE][BOARD_SIZE];

        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[y].length; ++x) {
                newBoard[y][x] = board[y][x];
            }
        }

        return newBoard;
    }

    private static boolean isMoveValid(char[][] board, Piece pieceInfo, Move move) {
        switch (pieceInfo.pieceName) {
            case 'k':
            case 'K':
                return isKingMoveValid(board, move);
            case 'q':
            case 'Q':
                return isQueenMoveValid(board, move);
            case 'b':
            case 'B':
                return isBishopMoveValid(board, move);
            case 'n':
            case 'N':
                return isKnightMoveValid(board, move);
            case 'r':
            case 'R':
                return isRookMoveValid(board, move);
            case 'p':
            case 'P':
                return isPawnMoveValid(board, move);
        }
        return false;
    }

    private static boolean isBishopMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

//        System.out.println(fromPiece + " , " + toPiece);

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        if (Math.abs(move.fromX - move.toX) != Math.abs(move.fromY - move.toY)) {
            return false;
        }

        int xIncrement = move.fromX < move.toX ? 1 : -1;
        int yIncrement = move.fromY < move.toY ? 1 : -1;

        int x = move.fromX + xIncrement;
        int y = move.fromY + yIncrement;

        while (x != move.toX && y != move.toY) {
            if (board[y][x] != 0 && x != move.toX && y != move.toY) {
                return false;
            }

            x += xIncrement;
            y += yIncrement;
        }

        return true;
    }

    private static boolean isRookMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        if (move.fromX == move.toX) {
            int yIncrement = move.fromY < move.toY ? 1 : -1;

            int y = move.fromY + yIncrement;

            while (y != move.toY) {
                if (board[y][move.fromX] != 0) {
                    return false;
                }

                y += yIncrement;
            }

            return true;

        } else if (move.fromY == move.toY) {
            int xIncrement = move.fromX < move.toX ? 1 : -1;

            int x = move.fromX + xIncrement;

            while (x != move.toX) {
                if (board[move.fromY][x] != 0) {
                    return false;
                }

                x += xIncrement;
            }

            return true;
        }

        return false;
    }

    private static boolean isKnightMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        for (int i = 0; i < knightMoveOffsets.length; ++i) {
            if (move.fromX + knightMoveOffsets[i][0] == move.toX && move.fromY + knightMoveOffsets[i][1] == move.toY) {
                return true;
            }
        }

        return false;
    }

    private static boolean isQueenMoveValid(char[][] board, Move move) {
        return isBishopMoveValid(board, move) || isRookMoveValid(board, move);
    }

    private static boolean isKingMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        if (toPiece != 0 && Character.isLowerCase(fromPiece) == Character.isLowerCase(toPiece)) {
            return false;
        }

        for (int i = 0; i < kingMoveOffsets.length; ++i) {
            if (move.fromX + kingMoveOffsets[i][0] == move.toX && move.fromY + kingMoveOffsets[i][1] == move.toY) {
                return true;
            }
        }

        return false;
    }

    private static boolean isPawnMoveValid(char[][] board, Move move) {
        char fromPiece = board[move.fromY][move.fromX];
        char toPiece = board[move.toY][move.toX];

        boolean isFromPieceWhite = Character.isLowerCase(fromPiece);
        boolean isToPieceWhite = Character.isLowerCase(toPiece);

        if (toPiece != 0 && isFromPieceWhite == isToPieceWhite) {
            return false;
        }

        if (toPiece != 0 && move.fromX == move.toX) {
            return false;
        }

        boolean hasMoved = isFromPieceWhite ? move.fromY != 6 : move.fromY != 1;

        if (!hasMoved && move.fromX == move.toX && Math.abs(move.toY - move.fromY) == 2) {
            if (move.toY > move.fromY && !isFromPieceWhite && board[move.toY - 1][move.toX] == 0) {
                return true;
            }

            return move.toY < move.fromY && isFromPieceWhite && board[move.toY + 1][move.toX] == 0;
        } else if (move.fromX == move.toX && Math.abs(move.toY - move.fromY) == 1) {
            if (move.toY > move.fromY && !isFromPieceWhite) {
                return true;
            }

            return move.toY < move.fromY && isFromPieceWhite;
        } else if (move.toX == move.fromX - 1 || move.toX == move.fromX + 1) {
            if (toPiece != 0 && isToPieceWhite != isFromPieceWhite) {
                return isFromPieceWhite ? move.toY == move.fromY - 1 : move.toY == move.fromY + 1;
            }
        }

        return false;
    }
}