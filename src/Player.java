public class Player {
    private String name;
    private int health;
    private int gold;
    private boolean hasHealthPot;
    private Weapon weapon;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
