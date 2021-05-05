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

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        return null;
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        return null;
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