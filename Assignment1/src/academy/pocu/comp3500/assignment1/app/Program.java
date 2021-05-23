package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public class Program {

    public static void main(String[] args) {
//        {
//            GameStat[] gameStats = new GameStat[] {
//                    new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
//                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
//                    new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
//                    new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
//                    new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
//                    new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
//                    new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
//                    new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
//                    new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
//                    new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
//                    new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
//            };
//
//            Player[] players = new Player[] {
//                    new Player(),
//                    new Player(),
//                    new Player(),
//                    new Player()
//            };
//
//            PocuBasketballAssociation.processGameStats(gameStats, players);
//
//            for (int i = 0; i < players.length; ++i) {
//                System.out.println("PlayerName > " + players[i].getName());
//                System.out.println(" 평균 득점 > " + players[i].getPointsPerGame());
//                System.out.println(" 평균 도움 > " + players[i].getAssistsPerGame());
//                System.out.println(" 평균 패스 > " + players[i].getPassesPerGame());
//                System.out.println(" 평균 성공률 > " + players[i].getShootingPercentage() + "%");
//            }
//        }

//        {
//            GameStat[] gameStats = new GameStat[]{
//                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
//                    //new GameStat("Player 2", 1, 2, 1, 10, 12, 2),
//                    //new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
//                    //new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
//            };
//            Player[] players = new Player[]{
//                    new Player()
//            };
//
//            PocuBasketballAssociation.processGameStats(gameStats, players);
//
//            for (int i = 0; i < players.length; ++i) {
//                System.out.println("PlayerName > " + players[i].getName());
//                System.out.println(" 평균 득점 > " + players[i].getPointsPerGame());
//                System.out.println(" 평균 도움 > " + players[i].getAssistsPerGame());
//                System.out.println(" 평균 패스 > " + players[i].getPassesPerGame());
//                System.out.println(" 평균 성공률 > " + players[i].getShootingPercentage() + "%");
//            }
//        }
//        {
//            Player[] players = new Player[] {
//                    new Player("Player 1", 1, 5, 1, 60),
//                    new Player("Player 2", 5, 2, 11, 31),
//                    new Player("Player 3", 7, 4, 7, 44),
//                    new Player("Player 4", 10, 10, 15, 25),
//                    new Player("Player 5", 11, 12, 6, 77),
//                    new Player("Player 6", 15, 0, 12, 61),
//                    new Player("Player 7", 16, 8, 2, 70)
//            };
//
//            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12); // player: Player 5
//            //System.out.println(player.getName());
//
//            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5); // player: Player 2
//            //System.out.println(player.getName());
//
//            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13); // player: Player 6
//            //System.out.println(player.getName());
//        }
//
//        {
//            Player[] players = new Player[] {
//                    new Player("Player 4", 10, 10, 15, 25),
//                    new Player("Player 2", 5, 2, 11, 31),
//                    new Player("Player 3", 7, 4, 7, 44),
//                    new Player("Player 1", 1, 5, 1, 60),
//                    new Player("Player 6", 15, 0, 12, 61),
//                    new Player("Player 7", 16, 8, 2, 70),
//                    new Player("Player 5", 11, 12, 6, 77)
//            };
//
//            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28); // player: Player 2
//            System.out.println(player.getName());
//
//            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58); // player: Player 1
//            System.out.println(player.getName());
//
//            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72); // player: Player 7
//            System.out.println(player.getName());
//        }

        {
            Player[] players = new Player[]{
                    /*
                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
                     */
                    new Player("Player 1", 5, 49, 28, 50),
                    new Player("Player 2", 15, 32, 19, 40),
                    new Player("Player 3", 11, 0, 22, 54),
                    new Player("Player 4", 10, 30, 5, 88),
                    new Player("Player 5", 16, 47, 22, 77),
                    new Player("Player 6", 1, 22, 35, 22),
                    new Player("Player 7", 42, 10, 48, 56),
                    new Player("Player 8", 33, 43, 30, 72),
                    new Player("Player 9", 33, 44, 12, 72),
                    new Player("Player 10", 33, 38, 34, 72),
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];


            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]

            System.out.println(outPlayers[0].getName());
            System.out.println(outPlayers[1].getName());
            System.out.println(outPlayers[2].getName());
        }
    }
}
