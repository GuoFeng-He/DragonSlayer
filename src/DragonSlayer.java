import java.util.Scanner;
public class DragonSlayer {
    private Scanner scan;
    private Player player;
    public static int difficultyScale;
    public static boolean gameOver;
    public boolean quit;
    public static int skillPoints;
    public static int highScore;
    public DragonSlayer(){
        scan = new Scanner(System.in);
        quit = false;

        System.out.print("Enter your adventurer's name: ");
        String name = scan.nextLine();
        player = new Player(name);
        introDialogue();
        while (!quit) {
            int choice = chooseDifficulty();
            gameOver = false;
            if (choice == 3){
                quit = true;
            } else {
                setDifficultyScale(choice);
                play();
            }
        }
    }

    public void play(){
        while (!gameOver && Room.roomNum < 6){
            Room newRoom = new Room();
            skillPoints = 0;
            while (!newRoom.isCleared() && !gameOver) {
                newRoom.printRoomInfo();
                newRoom.printMobs();

                player.printStats();
                player.act(newRoom);
                if (Player.burnDuration > 0) {
                    player.burnDamage();
                }
                for (Dragon dragon: newRoom.getMobs()){
                    if (dragon != null && dragon.getHealth() > 0) {
                        dragon.ability(player, newRoom.getMobs());
                    }
                    if (player.getHealth() < 0){
                        gameOver = true;
                    }
                }
                newRoom.cleared();
            }
            int intermission = 0;
            while (intermission != 8 && !gameOver){
                System.out.println("\nIntermission!");
                System.out.println("1. Search Room");
                System.out.println("2. Print Armor Stats");
                System.out.println("3. Print Weapon Stats");
                System.out.println("4. Switch Armor");
                System.out.println("5. Switch Weapon");
                System.out.println("6. Upgrade Current Armor");
                System.out.println("7. Upgrade Current Weapon");
                System.out.println("8. Exit");
                intermission = scan.nextInt();
                System.out.println();
                intermission(intermission, newRoom);
            }
        }
        int score = player.getGold() * 100;
        if (player.getHealth() < 0){
            System.out.println("You died!");
            System.out.println("Final Score: " + score);
        } else {
            System.out.println("GG you won!");
            System.out.println("Final Score: " + score);
        }
        if (score > highScore){
            highScore = score;
        }
        System.out.println("Input anything to return to the main menu: ");
        scan.nextLine();
    }

    public void introDialogue(){
        System.out.println("Hello " + player.getName());
        System.out.println("Welcome to the dungeons!");
        System.out.println("Loads of treasure await you but threats do too!");
        System.out.println("I gave you some starting gear to start out with");
        System.out.println("Good luck!");
        System.out.println();
    }

    public int chooseDifficulty(){
        System.out.println("1. Normal Mode: Standard Difficulty");
        System.out.println("2. Master Mode: A Harder Challenge! <highly recommended to beat normal mode first>");
        System.out.println("3. Quit Game");
        System.out.println("Choose difficulty (1/2):");
        int mode = 0;
        while (mode != 1 && mode != 2 && mode != 3){
            mode = scan.nextInt();
            if (mode != 1 && mode != 2 && mode != 3){
                System.out.println("Invalid choice, try again.");
            }
        }
        return mode;
    }

    public void setDifficultyScale(int difficulty){
        if (difficulty == 1){
            difficultyScale = 1;
        } else {
            difficultyScale = 2;
        }
    }

    public void intermission(int choice, Room room){
        if (choice == 1){
            room.searchRoom();
        } else if (choice == 2){
            player.getArmor().printStats();
        } else if (choice == 3){
            player.getWeapon().printStats();
        } else if (choice == 4){
            player.printArmorInventory();
            System.out.print("Choose armor to change to: ");
            int armor = scan.nextInt() - 1;
            player.changeArmor(player.getArmorInventory()[armor]);
        } else if (choice == 5){
            player.printWeaponInventory();
            System.out.println("Choose weapon to change to: ");
            int weapon = scan.nextInt() - 1;
            player.changeWeapon(player.getWeaponInventory()[weapon]);
        } else if (choice == 6){
            System.out.println("You are upgrading " + player.getArmor().getName());
            System.out.println("This process will consume 1 upgrade shard (You have " + Player.upgradeShards + ")");
            System.out.println("Proceed? (y/n)");
            scan.nextLine();
            String confirm = scan.nextLine();
            confirm = confirm.toLowerCase();

            if (confirm.equals("y") && Player.upgradeShards > 0){
                player.getArmor().upgrade();
            } else if (Player.upgradeShards == 0){
                System.out.println("You do not have any upgrade shards");
            } else if (confirm.equals("n")){
                System.out.println("Going back");
            } else {
                System.out.println("Invalid Choice");
            }
        } else if (choice == 7){
            System.out.println("You are upgrading " + player.getWeapon().getName());
            System.out.println("This process will consume 1 upgrade shard (You have " + Player.upgradeShards + ")");
            System.out.println("Proceed? (y/n)");
            scan.nextLine();
            String confirm = scan.nextLine();
            confirm = confirm.toLowerCase();

            if (confirm.equals("y") && Player.upgradeShards > 0){
                player.getWeapon().upgrade();
            } else if (Player.upgradeShards == 0){
            System.out.println("You do not have any upgrade shards");
            } else if (confirm.equals("n")){
                System.out.println("Going back");
            } else {
                System.out.println("Invalid Choice");
            }
        } else {
            System.out.println("Leaving intermission!");
        }
    }

}