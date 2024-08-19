import java.util.*;

class SnakeAndLadder11 {
    static int[][] board;
    static PriorityQueue<Integer> playerScore = new PriorityQueue<>(Comparator.reverseOrder());;
    static HashMap<String, Integer> PlayerData, tournamentData;
    static HashMap<String, String> colorcode;
    static String Name, Name1, Stop = "\u001B[0m";
    static int remove, boardNumber, firstDim, secondDim;
    static String[] colour = { "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m" };

    static void displayBoard(int c) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == firstDim && j == secondDim) {
                    print();
                } else {
                    if (i == 9 && j != 9) {
                        System.out.print("  " + board[i][j] + " ");
                    } else if (i == 9 && j == 9) {
                        System.out.print(" " + board[i][j] + "   ");
                    } else if (i != 0 && j == 0) {
                        System.out.print(" " + board[i][j] + "  ");
                    } else {
                        System.out.print(board[i][j] + "  ");
                    }
                }
            }
            System.out.println();
        }
    }

    static void insertPlayerData(int c) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        PlayerData = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            System.out.println("\nEnter Name of Player:" + i);
            PlayerData.put(sc.next(), 0);
        }
        colorcode = new HashMap<>();
        Set s = PlayerData.keySet();
        Iterator apply = s.iterator();
        while (apply.hasNext() && c != 8) {
            if (c == 2) {
                String player = (String) apply.next();
                String player1 = (String) apply.next();
                colorcode.put(player, colour[0]);
                colorcode.put(player1, colour[1]);
                System.out.println("\n" + colour[0] + player + Stop + " token color is Red. ");
                System.out.println(colour[1] + player1 + Stop + " token color is Green.\n ");
            } else if (c == 3) {
                String player = (String) apply.next();
                String player1 = (String) apply.next();
                String player2 = (String) apply.next();
                colorcode.put(player, colour[0]);
                colorcode.put(player1, colour[1]);
                colorcode.put(player2, colour[2]);
                System.out.println("\n" + colour[0] + player + Stop + " token color is Red. ");
                System.out.println(colour[1] + player1 + Stop + " token color is Green.");
                System.out.println(colour[2] + player2 + Stop + " token color is Yellow.");
            } else if (c == 4) {
                String player = (String) apply.next();
                String player1 = (String) apply.next();
                String player2 = (String) apply.next();
                String player3 = (String) apply.next();
                colorcode.put(player, colour[0]);
                colorcode.put(player1, colour[1]);
                colorcode.put(player2, colour[2]);
                colorcode.put(player3, colour[3]);
                System.out.println("\n" + colour[0] + player + Stop + " token color is Red.");
                System.out.println(colour[1] + player1 + Stop + " token color is Green.");
                System.out.println(colour[2] + player2 + Stop + " token color is Yellow.");
                System.out.println(colour[3] + player3 + Stop + " token color is Blue.");
            }
        }
        if (c != 8) {
            System.out.println("\n\u001B[35mLet's Start Game  \u001B[0m\n\u001B[36mReady: \u001B[0m ");
            System.out.println("\u001B[31m3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1\u001B[0m");
            Thread.sleep(1000);
            System.out.println("\u001B[32mGo...\u001B[0m");
            Thread.sleep(1000);
            gameMode(c);
        } else {
            System.out.println("Tournament Started");
            Tournament();
        }
    }

    static String secondPlayerName(Set s) {
        int c = 0;
        Iterator itr = s.iterator();
        while (itr.hasNext() && c != 1) {
            Name1 = (String) itr.next();
            c++;
        }
        return Name1;
    }

    static void colorTournament() {
        int a = 0;
        colorcode = new HashMap<>();
        Set sS = PlayerData.keySet();
        Iterator apply = sS.iterator();
        while (apply.hasNext() && a != 1) {
            a++;
            String player = (String) apply.next();
            String player1 = (String) apply.next();
            colorcode.put(player, colour[0]);
            colorcode.put(player1, colour[1]);
            System.out.println("\n" + colour[0] + player + Stop + " token color is Red. ");
            System.out.println(colour[1] + player1 + Stop + " token color is Green.\n ");
        }
    }

    static void Tournament() throws InterruptedException {

        Set s = PlayerData.keySet();
        tournamentData = new HashMap<>();
        System.out.println(
                "Schedule will be like: \n --> 4 League Matches. \n --> 2 Semi- Finals \n --> Finals");
        for (int i = 1; i <= 4; i++) {
            colorTournament();
            System.out.println(i + " League Match Started");
            gameMode(8);
            PlayerData.remove(Name);
            Name1 = secondPlayerName(s);
            PlayerData.remove(Name1);
            tournamentData.put(Name, 100);
            colorcode.clear();
        }

        PlayerData.clear();
        s = tournamentData.keySet();
        Iterator itr = s.iterator();
        System.out.println("League Match Winner's");
        while (itr.hasNext()) {
            String change = (String) itr.next();
            System.out.println(change);
            tournamentData.put(change, 0);
        }
        PlayerData.putAll(tournamentData);
        tournamentData.clear();
        for (int i = 1; i <= 2; i++) {
            colorTournament();
            System.out.println(i + " Semi Finals Match Started");
            gameMode(8);
            PlayerData.remove(Name, 100);
            Name1 = secondPlayerName(s);
            PlayerData.remove(Name1);
            tournamentData.put(Name, 100);
            colorcode.clear();
        }

        PlayerData.clear();
        s = tournamentData.keySet();
        itr = s.iterator();
        System.out.println("Semi Finals Winner's");
        while (itr.hasNext()) {
            String change = (String) itr.next();
            System.out.println(change);
            PlayerData.put(change, 0);
        }
        System.out.println("Final Match Started");
        colorTournament();
        gameMode(2);

        System.out.println("Final Winner is :");
        System.out.println(Name);
    }

    static void gameMode(int numberOfPlayers) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Set s = PlayerData.keySet();
        Collection<Integer> value = PlayerData.values();
        L1: for (;;) {
            Iterator scoress = value.iterator();
            Iterator itr = s.iterator();
            int i = 0;

            while ((itr.hasNext() && numberOfPlayers == 8 && i != 2) || (itr.hasNext() && numberOfPlayers != 8)) {
                Name = (String) itr.next();
                int point = (int) scoress.next();
                int dice = (int) ((Math.random() * 6) + 1);
                point = point + dice;
                System.out.println("\n" + Name + "=" + point + "\nDice Value=" + dice + "\n");
                point = conditionCheck(point);
                Thread.sleep(500);

                if (point == 100) {
                    System.out.println("Winner :" + Name);
                    PlayerData.put(Name, point);
                    printScoreCard(numberOfPlayers);
                    displayBoard(numberOfPlayers);
                    playerScore.clear();
                    break L1;
                }
                if (point > 100) {
                    point -= dice;
                    System.out.println("\n\nSorry Your Score Goes Above 100, It is an Invalid");
                    System.out.println("So Your Last roll is not Counted\n");
                    System.out.println("\n" + Name + "=" + point);
                    Thread.sleep(1500);
                }
                PlayerData.put(Name, point);
                i++;
                boardNumber++;
                if (boardNumber % 6 == 0) {
                    printScoreCard(numberOfPlayers);
                    displayBoard(numberOfPlayers);
                    playerScore.clear();
                    System.out.println("Press any Key");
                    char ch = sc.next().charAt(0);
                }
            }
        }
    }

    // To print ScoreCard before priting every Boards.--
    static void printScoreCard(int c) {

        Set s = PlayerData.keySet();
        Iterator itr = s.iterator();
        int n, a = 0;

        while ((itr.hasNext() && c == 8 && a != 2) || (itr.hasNext() && c != 8)) {
            String player_name = (String) itr.next();
            String player_color = (String) colorcode.get(player_name);
            n = PlayerData.get(player_name);
            System.out.println(player_color + "" + player_name + "=" + n + Stop);
            a++;
            playerScore.add(n);
        }
        // Je if ini value mod 1 hase then ene change karse
        remove = playerScore.poll();
        findLoc(remove);
    }

    static void print() {
        Set set = PlayerData.keySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            String name = (String) itr.next();
            int score = (int) PlayerData.get(name);
            if (remove == score) {
                String color = (String) colorcode.get(name);
                if (firstDim == 9 && secondDim != 9) {
                    System.out.print(color + "  " + board[firstDim][secondDim] + Stop + " ");
                } else if (firstDim == 9 && secondDim == 9) {
                    System.out.print(color + " " + board[firstDim][secondDim] + Stop + "   ");
                } else if (firstDim != 0 && secondDim == 0) {
                    System.out.print(color + " " + board[firstDim][secondDim] + Stop + "  ");
                } else {
                    System.out.print(color + "" + board[firstDim][secondDim] + Stop + "  ");
                }
                break;
            }
        }
        if (playerScore.size() != 0) {
            remove = playerScore.poll();
            findLoc(remove);
        }
    }

    static void findLoc(int n) {
        int a;
        a = n / 10;
        if (n % 10 != 0) {
            firstDim = 10 - a - 1;
        } else {
            firstDim = 10 - (a - 1) - 1;
        }
        if (firstDim % 2 != 0) {
            if (n % 10 != 0) {
                secondDim = (n % 10) - 1;
            } else {
                secondDim = 9;
            }
        } else {
            if (n % 10 != 0) {
                secondDim = 10 - (n % 10);
            } else {
                secondDim = 0;
            }
        }
    }

    static int conditionCheck(int point) throws InterruptedException {
        int a = point;
        if (point == 1) {
            point = 38;
            System.out.println("\u001B[32m Wahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 4) {
            point = 14;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 9) {
            point = 31;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 21) {
            point = 42;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 51) {
            point = 67;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 28) {
            point = 84;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 72) {
            point = 91;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 81) {
            point = 99;
            System.out.println("\u001B[32mWahhh!! You have a Ladder from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 17) {
            point = 7;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 62) {
            point = 19;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 54) {
            point = 34;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 64) {
            point = 60;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 87) {
            point = 36;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 93) {
            point = 73;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 94) {
            point = 75;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        } else if (point == 98) {
            point = 79;
            System.out.println("\u001B[31mOhhh,Sorry You have a Snake from :" + a + " to :" + point + "\u001B[0m\n");
        }
        Thread.sleep(2000);
        return point;
    }

    static void fun() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("\n1. 2 Players");
            System.out.println("2. 3 Players");
            System.out.println("3. 4 Players");
            System.out.println("4. Exit");
            System.out.println("Enter Your Choice");
            switch (sc.nextInt()) {
                case 1: {
                    insertPlayerData(2);
                    PlayerData.clear();
                    break;
                }
                case 2: {
                    insertPlayerData(3);
                    PlayerData.clear();
                    break;
                }
                case 3: {
                    insertPlayerData(4);
                    PlayerData.clear();
                    break;
                }
                case 4: {
                    check = false;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        board = new int[10][10];
        int j = 0;
        int number = 100;
        for (int i = 0; i < 10; i++) {
            while ((j != 10 && i % 2 == 0) || (j != 0 && i % 2 != 0)) {
                if (i % 2 == 0) {
                    board[i][j] = number--;
                    j++;
                } else if (i % 2 != 0) {
                    j--;
                    board[i][j] = number--;
                }
            }
        }
        boolean check = true;
        while (check) {
            System.out.println("1.Just Play for Fun");
            System.out.println("2.Organize Tournament");
            System.out.println("3.Exit");
            System.out.println("Enter Your Choice");
            switch (sc.nextInt()) {
                case 1: {
                    fun();
                    break;
                }
                case 2: {
                    L2: for (;;) {
                        System.out.println("You Should complusory Have 8 Players");
                        System.out.println("Have(YES) or Not(NO)");
                        String check1 = sc.next();
                        if (("YES").equalsIgnoreCase(check1)) {
                            insertPlayerData(8);
                            break L2;
                        } else if (("NO").equalsIgnoreCase(check1)) {
                            System.out.println("Sorry,You Are Not elegible to Organize Tournament");
                            break L2;
                        } else {
                            System.out.println("Enter valid Input");
                            System.out.println("Enter Again \n \n");
                        }
                    }
                    break;
                }
                case 3: {
                    check = false;
                    break;
                }
            }
        }
    }
}