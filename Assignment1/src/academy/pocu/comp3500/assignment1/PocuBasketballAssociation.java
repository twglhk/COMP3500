package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    /*
     * 각 선수마다 필요한 정보
     * 1. 선수의 이름
     * 2. 경기당 평균 득점 수
     * 3. 경기당 평균 어시스트 수
     * 4. 경기당 평균 패스 수
     * 5. 슛 성공률, 슛 성공률 = 100 * [총 슛 성공 수] / [총 슛 시도 수], 계산 후에 소수점 버림
     * */
    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        gameStatQuickSort(gameStats);
        var playersCursor = 0;
        var playerName = gameStats[0].getPlayerName();
        var totalGames = 0;
        int totalGoals = 0;
        int totalGoalAttempts = 0;
        int totalPoints = 0;
        int totalAssist = 0;
        int totalPasses = 0;

        for (int i = 0; i < gameStats.length; ++i) {
            if (!gameStats[i].getPlayerName().equals(playerName)) {
                outPlayers[playersCursor].setName(playerName);
                outPlayers[playersCursor].setPointsPerGame(totalPoints / totalGames);
                outPlayers[playersCursor].setAssistsPerGame(totalAssist / totalGames);
                outPlayers[playersCursor].setPassesPerGame(totalPasses / totalGames);
                outPlayers[playersCursor].setShootingPercentage((int) ((float) 100 * (float) totalGoals / (float) totalGoalAttempts));

                playerName = gameStats[i].getPlayerName();
                playersCursor++;
                totalGames = 0;
                totalGoals = 0;
                totalGoalAttempts = 0;
                totalPoints = 0;
                totalAssist = 0;
                totalPasses = 0;
            }

            totalGames++;
            totalGoals += gameStats[i].getGoals();
            totalGoalAttempts += gameStats[i].getGoalAttempts();
            totalPoints += gameStats[i].getPoints();
            totalAssist += gameStats[i].getAssists();
            totalPasses += gameStats[i].getNumPasses();
        }

        // 마지막 선수 저장
        outPlayers[playersCursor].setName(playerName);
        outPlayers[playersCursor].setPointsPerGame(totalPoints / totalGames);
        outPlayers[playersCursor].setAssistsPerGame(totalAssist / totalGames);
        outPlayers[playersCursor].setPassesPerGame(totalPasses / totalGames);
        outPlayers[playersCursor].setShootingPercentage((int) ((float) 100 * (float) totalGoals / (float) totalGoalAttempts));
    }

    private static void gameStatQuickSort(final GameStat[] gameStats) {
        gameStatQuickSortRecursive(gameStats, 0, gameStats.length - 1);
    }

    private static void gameStatQuickSortRecursive(final GameStat[] gameStats, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = gameStatPartition(gameStats, left, right);

        gameStatQuickSortRecursive(gameStats, left, pivotPos - 1);
        gameStatQuickSortRecursive(gameStats, pivotPos + 1, right);
    }

    private static int gameStatPartition(final GameStat[] gameStats, int left, int right) {
        int pivot = gameStats[right].getPlayerName().hashCode();
        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (gameStats[j].getPlayerName().hashCode() < pivot) {
                ++i;
                gameStatSwap(gameStats, i, j);
            }
        }

        int pivotPos = i + 1;
        gameStatSwap(gameStats, pivotPos, right);

        return pivotPos;
    }

    private static void gameStatSwap(final GameStat[] gameStats, int pos1, int pos2) {
        GameStat temp = gameStats[pos1];
        gameStats[pos1] = gameStats[pos2];
        gameStats[pos2] = temp;
    }

    public static Player pointsPerGameBinarySearchRecursive(final Player[] players, Player targetPlayer, int targetPoints, int left, int right) {
        if (left > right)
            return targetPlayer;

        int mid = (left + right) / 2;
        int targetPlayersPointGap = Math.abs(targetPlayer.getPointsPerGame() - targetPoints);
        int midPlayersPointGap = Math.abs(players[mid].getPointsPerGame() - targetPoints);

        // 타깃 플레이어의 갭과 mid 플레이어의 갭이 같을 경우
        if (targetPlayersPointGap == midPlayersPointGap) {
            // 둘의 평득을 비교해서 mid 플레이어의 평득이 높으면 타깃 플레이어를 mid로 교체
            if (targetPlayer.getPointsPerGame() < players[mid].getPointsPerGame())
                targetPlayer = players[mid];
        }

        // 타깃 플레이어의 갭보다 mid의 갭이 더 작을 경우 타깃 플레이어를 mid로 교체
        else if (targetPlayersPointGap > midPlayersPointGap) {
            targetPlayer = players[mid];
        }

        // 이외의 경우에는 타깃 플레이어 유지
        // 이진 탐색 재귀 시작. 동점인 플레이어가 있을 수 있기 때문에 targetPoints와 일치해도 모두 탐색 진행
        if (players[mid].getPointsPerGame() > targetPoints) {
            return pointsPerGameBinarySearchRecursive(players, targetPlayer, targetPoints, left, mid - 1);
        } else {
            return pointsPerGameBinarySearchRecursive(players, targetPlayer, targetPoints, mid + 1, right);
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        // pointsPerGame에 가장 가까운 선수들을 찾음 (별도의 컨테이너 저장은 불가능하니 순차적으로 탐색하면서 조건 검사)
        // 이진 탐색을 사용해서 가장 가까운 사람을 탐색할 것 (선수들이 경기당 득점 포인트의 오름 차순으로 정렬되어 있음)
        // 선형탐색 반복문에서 => 재귀로 작성할 것

        return pointsPerGameBinarySearchRecursive(players, players[(players.length - 1) / 2], targetPoints, 0, players.length - 1);


        // 초기값 업데이트
//        Player resultPlayer = players[0];
//        var minPointsGap = Math.abs(targetPoints - resultPlayer.getPointsPerGame());
//
//        for (int i = 1; i < players.length; ++i)
//        {
//            var pointsGap = Math.abs(targetPoints - players[i].getPointsPerGame());
//
//            // 이전 플레이어의 타깃 득점 차이보다 높다면 패스
//            if (pointsGap > minPointsGap)
//                continue;
//
//            // 이전에 득점 차이 보다 새로운 선수의 득점 차이가 적다면 그 사람으로 대체
//            else if (pointsGap < minPointsGap)
//            {
//                resultPlayer = players[i];
//                minPointsGap = pointsGap;
//                continue;
//            }
//
//            // 득점 차이가 동일하다면
//            else
//            {
//                //이전에 저장된 선수보다 평균 득점이 높을 경우에만 업데이트, 아니면 패스
//                if (resultPlayer.getPointsPerGame() < players[i].getPointsPerGame())
//                {
//                    resultPlayer = players[i];
//                    minPointsGap = pointsGap;
//                }
//            }
//        }
//
//        return resultPlayer;
    }

    public static Player shootingPercentageBinarySearchRecursive(final Player[] players, Player targetPlayer, int targetSP, int left, int right) {
        if (left > right)
            return targetPlayer;

        int mid = (left + right) / 2;
        int targetPlayerSPGap = Math.abs(targetPlayer.getShootingPercentage() - targetSP);
        int midPlayerSPGap = Math.abs(players[mid].getShootingPercentage() - targetSP);

        // 타깃 플레이어의 갭과 mid 플레이어의 갭이 같을 경우
        if (targetPlayerSPGap == midPlayerSPGap) {
            // 둘의 평득을 비교해서 mid 플레이어의 슛 성공률이 높으면 타깃 플레이어를 mid로 교체
            if (targetPlayer.getShootingPercentage() < players[mid].getShootingPercentage())
                targetPlayer = players[mid];
        }

        // 타깃 플레이어의 갭보다 mid의 갭이 더 작을 경우 타깃 플레이어를 mid로 교체
        else if (targetPlayerSPGap > midPlayerSPGap) {
            targetPlayer = players[mid];
        }

        // 이외의 경우에는 타깃 플레이어 유지
        // 이진 탐색 재귀 시작. 동점인 플레이어가 있을 수 있기 때문에 targetPoints와 일치해도 모두 탐색 진행
        if (players[mid].getShootingPercentage() > targetSP) {
            return shootingPercentageBinarySearchRecursive(players, targetPlayer, targetSP, left, mid - 1);
        } else {
            return shootingPercentageBinarySearchRecursive(players, targetPlayer, targetSP, mid + 1, right);
        }
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        return shootingPercentageBinarySearchRecursive(players, players[(players.length - 1) / 2], targetShootingPercentage, 0, players.length - 1);
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {

        playerTeamworkPointQuickSort(players);

        outPlayers[0] = players[0];
        outPlayers[1] = players[1];
        outPlayers[2] = players[2];

        int maxTeamWorkPoint = (players[0].getPassesPerGame() + players[1].getPassesPerGame() + players[2].getPassesPerGame())
                * players[2].getAssistsPerGame();
        int maxPassesSum = players[0].getPassesPerGame() + players[1].getPassesPerGame() + players[2].getPassesPerGame();
        int currentPassesSum;

        scratchHeapInsertAndDelete(scratch, players[0]);
        scratchHeapInsertAndDelete(scratch, players[1]);

        for (int i = 3; i < players.length; ++i) {
            // 새 요소 삽입
            scratchHeapInsertAndDelete(scratch, players[i - 1]);
            currentPassesSum = scratch[1].getPassesPerGame() + scratch[2].getPassesPerGame() + players[i].getPassesPerGame();

            if (currentPassesSum > maxPassesSum) {
                if (maxTeamWorkPoint < currentPassesSum * players[i].getAssistsPerGame()) {
                    maxTeamWorkPoint = currentPassesSum * players[i].getAssistsPerGame();
                    maxPassesSum = currentPassesSum;
                    outPlayers[0] = scratch[1];
                    outPlayers[1] = scratch[2];
                    outPlayers[2] = players[i];
                }
            }
        }
        return maxTeamWorkPoint;
    }

    private static void scratchHeapInsertAndDelete(final Player[] scratch, Player player) {
        // 자리 찾아가기
        if (scratch[1] == null) {
            scratch[1] = player;
            return;
        }

        if (scratch[2] == null) {
            scratch[2] = player;
            return;
        }

        if (player.getPassesPerGame() > scratch[1].getPassesPerGame()) {
            if (player.getPassesPerGame() > scratch[2].getPassesPerGame()) {
                scratch[1] = scratch[2];
                scratch[2] = player;
            } else {
                scratch[1] = player;
            }
        }
    }

    private static void playerTeamworkPointQuickSort(final Player[] players) {
        playerTeamworkPointSortRecursive(players, 0, players.length - 1);
    }

    private static void playerTeamworkPointSortRecursive(final Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = playerTeamworkPointPartition(players, left, right);

        playerTeamworkPointSortRecursive(players, left, pivotPos - 1);
        playerTeamworkPointSortRecursive(players, pivotPos + 1, right);
    }

    private static int playerTeamworkPointPartition(final Player[] players, int left, int right) {
        int pivot = players[right].getAssistsPerGame();
        int i = (left - 1);
        for (int j = left; j < right; ++j) {
            if (players[j].getAssistsPerGame() > pivot) {
                ++i;
                playerSwap(players, i, j);
            }
        }

        int pivotPos = i + 1;
        playerSwap(players, pivotPos, right);

        return pivotPos;
    }

    private static void playerSwap(final Player[] players, int pos1, int pos2) {
        Player temp = players[pos1];
        players[pos1] = players[pos2];
        players[pos2] = temp;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }
}