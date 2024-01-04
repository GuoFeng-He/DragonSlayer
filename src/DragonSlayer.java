import java.util.Scanner;
public class DragonSlayer {
    private Scanner scan;
    private Player player;
    public DragonSlayer(){
        scan = new Scanner(System.in);
        System.out.print("Enter your adventurer's name: ");
        String name = scan.nextLine();
        player = new Player(name);
        System.out.println("Welcome " + name + "!");
    }

    public void play(){

    }

    public void introDialogue(){

    }
}