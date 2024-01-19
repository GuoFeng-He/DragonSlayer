import java.util.Scanner;
public class DragonSlayer {
    private Scanner scan;
    private Player player;
    public static double difficultyScale;
    public static boolean gameOver;
    public DragonSlayer(){
        scan = new Scanner(System.in);
        System.out.print("Enter your adventurer's name: ");
        String name = scan.nextLine();
        player = new Player(name);
        gameOver = false;
        introDialogue();
        while (!gameOver) {
            setDifficultyScale(chooseDifficulty());
            play();
        }
    }

    public void play(){
        while (!gameOver){

        }
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
        System.out.println("2. Master Mode: Pain incarnate");
        System.out.println("Choose difficulty (1/2):");
        int mode = 0;
        while (mode != 1 && mode != 2){
            mode = scan.nextInt();
            if (mode != 1 && mode != 2){
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
}