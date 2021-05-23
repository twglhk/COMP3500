package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        //testF();
        //TestG02();
        //testFindDreamTeam();
        //testFindDreamTeamSize();
        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 5, 17, 50),
                    new Player("Player 6", 15, 4, 10, 40),
                    new Player("Player 5", 11, 3, 25, 54),
                    new Player("Player 4", 10, 9, 1, 88),
                    new Player("Player 7", 16, 7, 5, 77),
                    new Player("Player 1", 1, 2, 8, 22),
                    new Player("Player 9", 42, 15, 4, 56),
                    new Player("Player 8", 33, 11, 3, 72),
            };

            int k = 4;
            Player[] outPlayers = new Player[4];
            Player[] scratch = new Player[k];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, 4, outPlayers, scratch); // maxTeamwork: 171, outPlayers: [ Player 6, Player 5, Player 2, Player 7 ]
            assert (maxTeamwork == 171);

        }



//        {
//            final int PLAYER_CNT = 10;
//            final int REQUEST_TEAM_SIZE = 5;
//            final int PASS_MAX = 10;
//            final int ASSIST_MAX = 10;
//            Player[] players3 = new Player[PLAYER_CNT];
//            Player[] players4 = players3.clone();
//            Player[] tempPlayers3 = new Player[players3.length];
//            Player[] out3 = new Player[players3.length];
//            Player[] out4 = new Player[players3.length];
//            for (int i = 0; i < players3.length; i++) {
//                players3[i] = new Player("", 0,
//                        0, 0, 0);
//                players4[i] = new Player("", 0,
//                        0, 0, 0);
//            }
//            int testCnt = 0;
//            while (testCnt < 100) {
//                for (int i = 0; i < players3.length; i++) {
//                    int passRand = (int) (Math.random()*PASS_MAX);
//                    int assistRand = (int) (Math.random()*ASSIST_MAX);
//                    players3[i].setName("Player " + (i + 1));
//                    players3[i].setPassesPerGame(passRand);
//                    players3[i].setAssistsPerGame(assistRand);
//                    players4[i].setName("Player " + (i + 1));
//                    players4[i].setPassesPerGame(passRand);
//                    players4[i].setAssistsPerGame(assistRand);
//                }
//                long curr = System.nanoTime();
//                long k = PocuBasketballAssociation.findDreamTeam(players3, REQUEST_TEAM_SIZE, out3, tempPlayers3);
//                long end = System.nanoTime();
//                long curr2 = System.nanoTime();
//                long k2 = PocuBasketballAssociation.findDreamTeam(players4, REQUEST_TEAM_SIZE, out4, tempPlayers3);
//                long end2 = System.nanoTime();
//                System.out.println("testTeamCnt " + REQUEST_TEAM_SIZE + " " +
//                        testCnt + " : time : " + (end - curr)/1000000 + " " + (end - curr)/1000 + " cnt " + k +
//                        " time2 : " + (end2 - curr2)/1000000 + " " + (end2 - curr2)/1000 + " cnt2 " + k2);
//                assert (k == k2);
//                testCnt++;
//            }
//        }
//
//        {
//            final int PLAYER_CNT = 1000;
//            final int PASS_MAX = 10;
//            final int ASSIST_MAX = 10;
//            Player[] players3 = new Player[PLAYER_CNT];
//            Player[] players4 = players3.clone();
//            for (int i = 0; i < players3.length; i++) {
//                players3[i] = new Player("", 0,
//                        0, 0, 0);
//                players4[i] = new Player("", 0,
//                        0, 0, 0);
//            }
//            int testCnt = 0;
//            Player[] tempPlayers3 = new Player[players3.length];
//            while (testCnt < 10) {
//                for (int i = 0; i < players3.length; i++) {
//                    int passRand = (int) (Math.random()*PASS_MAX);
//                    int assistRand = (int) (Math.random()*ASSIST_MAX);
//                    //int rand2 = 3;
//                    players3[i].setName("Player " + (i + 1));
//                    players3[i].setPassesPerGame(passRand + 1);
//                    players3[i].setAssistsPerGame(assistRand);
//                    players4[i].setName("Player " + (i + 1));
//                    players4[i].setPassesPerGame(passRand + 1);
//                    players4[i].setAssistsPerGame(assistRand);
//                }
//                long curr = System.nanoTime();
//                int k = PocuBasketballAssociation.findDreamTeamSize(players3, tempPlayers3);
//                long end = System.nanoTime();
//                long curr2 = System.nanoTime();
//                int k2 = PocuBasketballAssociation.findDreamTeamSize(players4, tempPlayers3);
//                long end2 = System.nanoTime();
//                testCnt++;
//                System.out.println("test " + testCnt + " : time : " + (end - curr)/1000000 + " " + (end - curr)/1000 + " cnt " + k +
//                        " time2 : " + (end2 - curr2)/1000000 + " " + (end2 - curr2)/1000 + " cnt2 " + k2);
//                assert (k == k2);
//            }
//        }

        // F02 test
        // 1.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100), (int)(Math.random()*100), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100), (int)(Math.random()*100), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100), (int)(Math.random()*100), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100), (int)(Math.random()*100), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100), (int)(Math.random()*100), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100), (int)(Math.random()*100), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100), (int)(Math.random()*100), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100), (int)(Math.random()*100), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100), (int)(Math.random()*100), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100), (int)(Math.random()*100), 72),
//                    */
//                    new Player("Player 1", 5, 52, 0, 50),
//                    new Player("Player 2", 15, 16, 96, 40),
//                    new Player("Player 3", 11, 55, 32, 54),
//                    new Player("Player 4", 10, 38, 84, 88),
//                    new Player("Player 5", 16, 0, 26, 77),
//                    new Player("Player 6", 1, 27, 40, 22),
//                    new Player("Player 7", 42, 64, 61, 56),
//                    new Player("Player 8", 33, 86, 34, 72),
//                    new Player("Player 9", 33, 58, 0, 72),
//                    new Player("Player 10", 33, 56, 6, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 6985);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 7");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 8");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 3");
//            assert (player != null);
//        }
//        // 2.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 35, 32, 50),
//                    new Player("Player 2", 15, 5, 10, 40),
//                    new Player("Player 3", 11, 7, 26, 54),
//                    new Player("Player 4", 10, 32, 15, 88),
//                    new Player("Player 5", 16, 48, 19, 77),
//                    new Player("Player 6", 1, 45, 46, 22),
//                    new Player("Player 7", 42, 25, 33, 56),
//                    new Player("Player 8", 33, 12, 35, 72),
//                    new Player("Player 9", 33, 30, 6, 72),
//                    new Player("Player 10", 33, 0, 35, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 3395);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 6");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 1");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 5");
//            assert (player != null);
//        }
//        // 3.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 35, 44, 50),
//                    new Player("Player 2", 15, 49, 40, 40),
//                    new Player("Player 3", 11, 44, 41, 54),
//                    new Player("Player 4", 10, 5, 22, 88),
//                    new Player("Player 5", 16, 3, 44, 77),
//                    new Player("Player 6", 1, 39, 24, 22),
//                    new Player("Player 7", 42, 10, 44, 56),
//                    new Player("Player 8", 33, 40, 22, 72),
//                    new Player("Player 9", 33, 43, 46, 72),
//                    new Player("Player 10", 33, 27, 27, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 5461);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 9");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 3");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 2");
//            assert (player != null);
//        }
//        // 4.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 48, 44, 50),
//                    new Player("Player 2", 15, 31, 19, 40),
//                    new Player("Player 3", 11, 19, 26, 54),
//                    new Player("Player 4", 10, 44, 7, 88),
//                    new Player("Player 5", 16, 4, 0, 77),
//                    new Player("Player 6", 1, 23, 14, 22),
//                    new Player("Player 7", 42, 48, 41, 56),
//                    new Player("Player 8", 33, 31, 42, 72),
//                    new Player("Player 9", 33, 43, 24, 72),
//                    new Player("Player 10", 33, 16, 8, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 4687);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 1");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 7");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 9");
//            assert (player != null);
//        }
//        // 5.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 49, 28, 50),
//                    new Player("Player 2", 15, 32, 19, 40),
//                    new Player("Player 3", 11, 0, 22, 54),
//                    new Player("Player 4", 10, 30, 5, 88),
//                    new Player("Player 5", 16, 47, 22, 77),
//                    new Player("Player 6", 1, 22, 35, 22),
//                    new Player("Player 7", 42, 10, 48, 56),
//                    new Player("Player 8", 33, 43, 30, 72),
//                    new Player("Player 9", 33, 44, 12, 72),
//                    new Player("Player 10", 33, 38, 34, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 3496);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 10");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 8");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 1");
//            assert (player != null);
//        }
//        // 6.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 14, 21, 50),
//                    new Player("Player 2", 15, 45, 6, 40),
//                    new Player("Player 3", 11, 24, 22, 54),
//                    new Player("Player 4", 10, 0, 28, 88),
//                    new Player("Player 5", 16, 40, 31, 77),
//                    new Player("Player 6", 1, 14, 7, 22),
//                    new Player("Player 7", 42, 44, 42, 56),
//                    new Player("Player 8", 33, 17, 8, 72),
//                    new Player("Player 9", 33, 24, 3, 72),
//                    new Player("Player 10", 33, 37, 20, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 3441);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 7");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 5");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 10");
//            assert (player != null);
//        }
//        // 7.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 13, 46, 50),
//                    new Player("Player 2", 15, 13, 4, 40),
//                    new Player("Player 3", 11, 10, 32, 54),
//                    new Player("Player 4", 10, 35, 23, 88),
//                    new Player("Player 5", 16, 21, 10, 77),
//                    new Player("Player 6", 1, 47, 39, 22),
//                    new Player("Player 7", 42, 0, 46, 56),
//                    new Player("Player 8", 33, 19, 26, 72),
//                    new Player("Player 9", 33, 48, 0, 72),
//                    new Player("Player 10", 33, 16, 32, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 2170);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 6");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 4");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 9");
//            assert (player != null);
//        }
//        // 8.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 15, 6, 50),
//                    new Player("Player 2", 15, 3, 49, 40),
//                    new Player("Player 3", 11, 3, 11, 54),
//                    new Player("Player 4", 10, 29, 17, 88),
//                    new Player("Player 5", 16, 19, 24, 77),
//                    new Player("Player 6", 1, 46, 31, 22),
//                    new Player("Player 7", 42, 10, 22, 56),
//                    new Player("Player 8", 33, 33, 19, 72),
//                    new Player("Player 9", 33, 37, 10, 72),
//                    new Player("Player 10", 33, 13, 43, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 1980);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 6");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 8");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 9");
//            assert (player != null);
//        }
//        // 9.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 3, 13, 50),
//                    new Player("Player 2", 15, 25, 9, 40),
//                    new Player("Player 3", 11, 26, 14, 54),
//                    new Player("Player 4", 10, 43, 45, 88),
//                    new Player("Player 5", 16, 22, 27, 77),
//                    new Player("Player 6", 1, 38, 6, 22),
//                    new Player("Player 7", 42, 36, 39, 56),
//                    new Player("Player 8", 33, 34, 5, 72),
//                    new Player("Player 9", 33, 15, 40, 72),
//                    new Player("Player 10", 33, 33, 5, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 3240);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 4");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 7");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 6");
//            assert (player != null);
//        }
//        // 10.
//        {
//            Player[] players = new Player[]{
//                    /*
//                    new Player("Player 1", 5, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 50),
//                    new Player("Player 2", 15, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 40),
//                    new Player("Player 3", 11, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 54),
//                    new Player("Player 4", 10, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 88),
//                    new Player("Player 5", 16, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 77),
//                    new Player("Player 6", 1, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 22),
//                    new Player("Player 7", 42, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 56),
//                    new Player("Player 8", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 9", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                    new Player("Player 10", 33, (int)(Math.random()*100%50), (int)(Math.random()*100%50), 72),
//                     */
//                    new Player("Player 1", 5, 14, 24, 50),
//                    new Player("Player 2", 15, 3, 46, 40),
//                    new Player("Player 3", 11, 5, 46, 54),
//                    new Player("Player 4", 10, 43, 33, 88),
//                    new Player("Player 5", 16, 40, 17, 77),
//                    new Player("Player 6", 1, 45, 6, 22),
//                    new Player("Player 7", 42, 43, 42, 56),
//                    new Player("Player 8", 33, 24, 11, 72),
//                    new Player("Player 9", 33, 32, 30, 72),
//                    new Player("Player 10", 33, 19, 10, 72),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 3680);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 7");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 4");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 5");
//            assert (player != null);
//        }
//        // 11.
//        {
//            Player[] players = new Player[]{
//                    new Player("Player 1", 5, 14, 24, 50),
//                    new Player("Player 2", 15, 3, 46, 40),
//                    new Player("Player 3", 11, 5, 46, 54),
//                    new Player("Player 4", 10, 43, 33, 88),
//            };
//
//            Player[] outPlayers = new Player[3];
//            Player[] scratch = new Player[3];
//
//            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);
//
//            assert (maxTeamwork == 515);
//
//            Player player = getPlayerOrNull(outPlayers, "Player 3");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 4");
//            assert (player != null);
//
//            player = getPlayerOrNull(outPlayers, "Player 1");
//            assert (player != null);
//        }
//    }
//
//    public static void TestG02() {
//        // Duplicated Assist
//        Player[] players = new Player[]{
//                new Player("Player 1", 2, 0, 100, 78),
//                new Player("Player 2", 0, 4, 40, 62),
//                new Player("Player 3", 10, 4, 0, 66),
//                new Player("Player 4", 3, 4, 50, 22),
//                new Player("Player 5", 1, 6, 7, 12),
//                new Player("Player 6", 11, 6, 24, 26),
//                new Player("Player 7", 7, 6, 4, 15),
//                new Player("Player 8", 8, 8, 2, 11),
//                new Player("Player 9", 5, 8, 10, 5),
//                new Player("Player 10", 8, 8, 5, 67)
//        };
//        final int TEAM_SIZE = 4;
//        Player[] outPlayers = new Player[TEAM_SIZE];
//        Player[] scratch = new Player[TEAM_SIZE];
//        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
//
//        assert (maxTeamwork == 124 * 4);
//        assert (getPlayerOrNull(outPlayers, "Player 2") != null);
//        assert (getPlayerOrNull(outPlayers, "Player 4") != null);
//        assert (getPlayerOrNull(outPlayers, "Player 6") != null);
//        assert (getPlayerOrNull(outPlayers, "Player 9") != null);
//    }
//
//    private static void testFindDreamTeam() {
//        // POCU test
//        Player[] players = new Player[]{
//                new Player("Player 2", 5, 5, 17, 50),
//                new Player("Player 6", 15, 4, 10, 40),
//                new Player("Player 5", 11, 3, 25, 54),
//                new Player("Player 4", 10, 9, 1, 88),
//                new Player("Player 7", 16, 7, 5, 77),
//                new Player("Player 1", 1, 2, 8, 22),
//                new Player("Player 9", 42, 15, 4, 56),
//                new Player("Player 8", 33, 11, 3, 72),
//        };
//
//        int TEAM_SIZE = 4;
//
//        Player[] outPlayers = new Player[TEAM_SIZE];
//        Player[] scratch = new Player[TEAM_SIZE];
//        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
//        assert (maxTeamwork == 171);
//        Player player = getPlayerOrNull(outPlayers, "Player 5");
//        assert (player != null);
//        player = getPlayerOrNull(outPlayers, "Player 6");
//        assert (player != null);
//        player = getPlayerOrNull(outPlayers, "Player 2");
//        assert (player != null);
//        player = getPlayerOrNull(outPlayers, "Player 7");
//        assert (player != null);
//
//        // my test
//        TEAM_SIZE = 1;
//        outPlayers = new Player[TEAM_SIZE];
//        scratch = new Player[TEAM_SIZE];
//        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
//        assert (maxTeamwork == 85);
//        player = getPlayerOrNull(outPlayers, "Player 2");
//        assert (player != null);
//        // my test 2
//        players = new Player[]{
//                new Player("Player 1", 1, 2, 8, 22),
//        };
//        maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);
//        assert (maxTeamwork == 16);
//        player = getPlayerOrNull(outPlayers, "Player 1");
//        assert (player != null);
//    }
//
//    private static Player getPlayerOrNull(final Player[] players, final String id) {
//        for (Player player : players) {
//            if (player.getName().equals(id)) {
//                return player;
//            }
//        }
//
//        return null;
//    }
//
//    public static void testF() {
//        {
//            System.out.print("ManDreamTeam: ");
//
//            Random random = new Random();
//
//            for (int testCount = 0; testCount < 1000; ++testCount) {
//                Player[] players = new Player[random.nextInt(20) + 3];
////                Player[] players = new Player[3];
//
//                for (int i = 0; i < players.length; ++i) {
//                    players[i] = new Player(String.format("Player %d", i + 1),
//                            random.nextInt(30),
//                            random.nextInt(30),
//                            random.nextInt(30),
//                            random.nextInt(30));
//                }
//                int maxTeamWork = -1;
//                ArrayList<Integer> firstPlayerIndices = new ArrayList<>();
//                ArrayList<Integer> secondPlayerIndices = new ArrayList<>();
//                ArrayList<Integer> thirdPlayerIndices = new ArrayList<>();
//                for (int j = 0; j < players.length - 2; ++j) {
//                    for (int k = j + 1; k < players.length - 1; ++k) {
//                        for (int l = k + 1; l < players.length; ++l) {
//                            int curTeamWork = players[j].getPassesPerGame() + players[k].getPassesPerGame() + players[l].getPassesPerGame();
//                            int minAssist = Math.min(Math.min(players[j].getAssistsPerGame(), players[k].getAssistsPerGame()), players[l].getAssistsPerGame());
//                            curTeamWork *= minAssist;
//
//                            if (curTeamWork > maxTeamWork) {
//                                maxTeamWork = curTeamWork;
//                                firstPlayerIndices.clear();
//                                secondPlayerIndices.clear();
//                                thirdPlayerIndices.clear();
//                                firstPlayerIndices.add(j);
//                                secondPlayerIndices.add(k);
//                                thirdPlayerIndices.add(l);
//                                System.out.println("New Max Team Work: " + maxTeamWork);
//                                System.out.println("New: " + players[firstPlayerIndices.get(0)].getName() + " pass: " + players[firstPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(0)].getAssistsPerGame());
//                                System.out.println("New: " + players[secondPlayerIndices.get(0)].getName() + " pass: " + players[secondPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(0)].getAssistsPerGame());
//                                System.out.println("New: " + players[thirdPlayerIndices.get(0)].getName() + " pass: " + players[thirdPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(0)].getAssistsPerGame());
//
//                            } else if (curTeamWork == maxTeamWork) {
//                                firstPlayerIndices.add(j);
//                                secondPlayerIndices.add(k);
//                                thirdPlayerIndices.add(l);
//
//                                System.out.println("Current Team Work: " + curTeamWork + " Team Work: " + maxTeamWork);
//                                System.out.println("New: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getName() + " pass: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getAssistsPerGame());
//                                System.out.println("New: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getName() + " pass: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getAssistsPerGame());
//                                System.out.println("New: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getName() + " pass: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getAssistsPerGame());
//                            }
//                        }
//                    }
//                }
//
//                Player[] outPlayers = new Player[3];
//                Player[] scratch = new Player[3];
//
//                System.out.print(System.lineSeparator());
//                for (Player player : players) {
//                    System.out.println(player.getName() + " pass: " + player.getPassesPerGame() + " assist: " + player.getAssistsPerGame());
//                }
//                Player[] copyOfPlayers = players.clone();
//                long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(copyOfPlayers, outPlayers, scratch);
//
//                System.out.println("Max Team Work: " + maxTeamWork + " max team work?: " + maxTeamwork);
//
//                for (Player player : outPlayers) {
//                    System.out.println("Result: " + player.getName() + " pass: " + player.getPassesPerGame() + " assist: " + player.getAssistsPerGame());
//                }
//
//                boolean hasAnswer = false;
//                for (int i = 0; i < firstPlayerIndices.size(); ++i) {
//                    System.out.println((i + 1) + ": Expected: " + players[firstPlayerIndices.get(i)].getName() + " pass: " + players[firstPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(i)].getAssistsPerGame());
//                    System.out.println((i + 1) + ": Expected: " + players[secondPlayerIndices.get(i)].getName() + " pass: " + players[secondPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(i)].getAssistsPerGame());
//                    System.out.println((i + 1) + ": Expected: " + players[thirdPlayerIndices.get(i)].getName() + " pass: " + players[thirdPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(i)].getAssistsPerGame());
//
//                    Player player1 = getPlayerOrNull(outPlayers, players[firstPlayerIndices.get(i)].getName());
//                    Player player2 = getPlayerOrNull(outPlayers, players[secondPlayerIndices.get(i)].getName());
//                    Player player3 = getPlayerOrNull(outPlayers, players[thirdPlayerIndices.get(i)].getName());
//
//                    hasAnswer = (player1 != null && player2 != null && player3 != null);
//
//                    if (hasAnswer) {
//                        break;
//                    }
//                }
//
//                assert (maxTeamwork == (long) maxTeamWork);
//                assert(hasAnswer);
//            }
//
//            System.out.println("SUCCESS");
//        }
//    }
//
//    private static void testFindDreamTeamSize() {
//        // POCU test
//        Player[] players = new Player[]{
//                new Player("Player 1", 2, 5, 10, 78),
//                new Player("Player 2", 10, 4, 5, 66),
//                new Player("Player 3", 3, 3, 2, 22),
//                new Player("Player 4", 1, 9, 8, 12),
//                new Player("Player 5", 11, 1, 12, 26),
//                new Player("Player 6", 7, 2, 10, 15),
//                new Player("Player 7", 8, 15, 3, 11),
//                new Player("Player 8", 5, 7, 13, 5),
//                new Player("Player 9", 8, 2, 7, 67),
//                new Player("Player 10", 1, 11, 0, 29),
//                new Player("Player 11", 2, 6, 9, 88)
//        };
//
//        Player[] tempPlayers = new Player[players.length];
//
//        int k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);
//
//        assert (k == 6 || k == 5);
//
//        // my test 1
//        players = new Player[]{
//                new Player("Player 1", 2, 1, 100, 78),
//                new Player("Player 2", 10, 1, 100, 66),
//                new Player("Player 3", 3, 1, 100, 22),
//                new Player("Player 4", 1, 100, 50, 12),
//                new Player("Player 5", 11, 1, 100, 26),
//                new Player("Player 6", 7, 1, 100, 15),
//                new Player("Player 7", 8, 1, 100, 11),
//        };
//
//        tempPlayers = new Player[players.length];
//
//        k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);
//
//        assert (k == 1);
//
//        // my test 2
//        players = new Player[]{
//                new Player("Player 1", 2, 1, 100, 78),
//        };
//
//        tempPlayers = new Player[players.length];
//
//        k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);
//
//        assert (k == 1);

    }
}