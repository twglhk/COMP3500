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
        for (int playerIndex = 0; playerIndex < outPlayers.length; ++playerIndex)
        {
            Player updateTargetPlayer = outPlayers[playerIndex];
            int totalGames = 0;
            int totalPoints = 0;
            int totalAssists = 0;
            int totalPasses = 0;
            int totalGoals = 0;
            int totalGoalAttempts = 0;

            for (int gameStatIndex = 0; gameStatIndex < gameStats.length; ++gameStatIndex)
            {
                if (gameStats[gameStatIndex] == null)
                    continue;

                if (updateTargetPlayer.getName() != null)
                {
                    if (updateTargetPlayer.getName() != gameStats[gameStatIndex].getPlayerName())
                        continue;
                }

                else
                    updateTargetPlayer.setName(gameStats[gameStatIndex].getPlayerName());

                // Data 세팅
                totalGames++;
                totalPoints += gameStats[gameStatIndex].getPoints();
                totalAssists += gameStats[gameStatIndex].getAssists();
                totalPasses += gameStats[gameStatIndex].getNumPasses();
                totalGoals += gameStats[gameStatIndex].getGoals();
                totalGoalAttempts += gameStats[gameStatIndex].getGoalAttempts();

                gameStats[gameStatIndex] = null;
            }

            updateTargetPlayer.setPointsPerGame((int)((float)totalPoints/(float)totalGames));
            updateTargetPlayer.setAssistsPerGame((int)((float)totalAssists/(float)totalGames));
            updateTargetPlayer.setPassesPerGame((int)((float)totalPasses/(float)totalGames));
            updateTargetPlayer.setShootingPercentage((int)(100f * (float)totalGoals / (float)totalGoalAttempts));

            // Test Print
//            System.out.println("게임 스탯 배열 사이즈 > " + gameStats.length);
//            System.out.println("PlayerName > " + updateTargetPlayer.getName());
//            System.out.println(" 평균 득점 > " + updateTargetPlayer.getPointsPerGame());
//            System.out.println(" 평균 도움 > " + updateTargetPlayer.getAssistsPerGame());
//            System.out.println(" 평균 패스 > " + updateTargetPlayer.getPassesPerGame());
//            System.out.println(" 평균 성공률 > " + updateTargetPlayer.getShootingPercentage() + "%");
        }
    }

    public static Player PointsPerGameBinarySearchRecurcive(final Player[] players, Player targetPlayer, int targetPoints, int left, int right)
    {
        if (left > right)
            return targetPlayer;

        int mid = (left + right) / 2;
        int targetPlayersPointGap = Math.abs(targetPlayer.getPointsPerGame() - targetPoints);
        int midPlayersPointGap = Math.abs(players[mid].getPointsPerGame() - targetPoints);

        // 타깃 플레이어의 갭과 mid 플레이어의 갭이 같을 경우
        if (targetPlayersPointGap == midPlayersPointGap)
        {
            // 둘의 평득을 비교해서 mid 플레이어의 평득이 높으면 타깃 플레이어를 mid로 교체
            if (targetPlayer.getPointsPerGame() < players[mid].getPointsPerGame())
                targetPlayer = players[mid];
        }

        // 타깃 플레이어의 갭보다 mid의 갭이 더 작을 경우 타깃 플레이어를 mid로 교체
        else if (targetPlayersPointGap > midPlayersPointGap)
        {
            targetPlayer = players[mid];
        }

        // 이외의 경우에는 타깃 플레이어 유지
        // 이진 탐색 재귀 시작. 동점인 플레이어가 있을 수 있기 때문에 targetPoints와 일치해도 모두 탐색 진행
        if (players[mid].getPointsPerGame() > targetPoints)
        {
            return PointsPerGameBinarySearchRecurcive(players, targetPlayer, targetPoints, left, mid-1);
        }
        else
        {
            return PointsPerGameBinarySearchRecurcive(players, targetPlayer, targetPoints, mid+1, right);
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        // pointsPerGame에 가장 가까운 선수들을 찾음 (별도의 컨테이너 저장은 불가능하니 순차적으로 탐색하면서 조건 검사)
        // 이진 탐색을 사용해서 가장 가까운 사람을 탐색할 것 (선수들이 경기당 득점 포인트의 오름 차순으로 정렬되어 있음)
        // 선형탐색 반복문에서 => 재귀로 작성할 것

        return PointsPerGameBinarySearchRecurcive(players, players[(players.length -1) / 2], targetPoints, 0, players.length -1);


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

    public static Player ShootingPercentageBinarySearchRecurcive(final Player[] players, Player targetPlayer, int targetSP, int left, int right)
    {
        if (left > right)
            return targetPlayer;

        int mid = (left + right) / 2;
        int targetPlayerSPGap = Math.abs(targetPlayer.getShootingPercentage() - targetSP);
        int midPlayerSPGap = Math.abs(players[mid].getShootingPercentage() - targetSP);

        // 타깃 플레이어의 갭과 mid 플레이어의 갭이 같을 경우
        if (targetPlayerSPGap == midPlayerSPGap)
        {
            // 둘의 평득을 비교해서 mid 플레이어의 슛 성공률이 높으면 타깃 플레이어를 mid로 교체
            if (targetPlayer.getShootingPercentage() < players[mid].getShootingPercentage())
                targetPlayer = players[mid];
        }

        // 타깃 플레이어의 갭보다 mid의 갭이 더 작을 경우 타깃 플레이어를 mid로 교체
        else if (targetPlayerSPGap > midPlayerSPGap)
        {
            targetPlayer = players[mid];
        }

        // 이외의 경우에는 타깃 플레이어 유지
        // 이진 탐색 재귀 시작. 동점인 플레이어가 있을 수 있기 때문에 targetPoints와 일치해도 모두 탐색 진행
        if (players[mid].getShootingPercentage() > targetSP)
        {
            return ShootingPercentageBinarySearchRecurcive(players, targetPlayer, targetSP, left, mid-1);
        }
        else
        {
            return ShootingPercentageBinarySearchRecurcive(players, targetPlayer, targetSP, mid+1, right);
        }
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        return ShootingPercentageBinarySearchRecurcive(players, players[(players.length -1) / 2], targetShootingPercentage, 0, players.length-1);
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }
}