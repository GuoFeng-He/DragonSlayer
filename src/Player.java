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

    public void changeWeapon(Weapon newWeapon){
        weapon = newWeapon;
    }

    public void changeCombatStats(int atk, int dodge, int health){

    }
}
