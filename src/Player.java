import java.util.Scanner;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int atk;
    private int dodge;
    private int gold;
    public static int healthPotAmount;
    public static int upgradeShards;
    public static int burnDuration;
    public static int burnDamage;
    private Weapon weapon;
    private Armor armor;
    private Scanner scan = new Scanner(System.in);
    private Armor[] armorInventory;
    private Weapon[] weaponInventory;

    public Player(String name){
        this.name = name;
        health = 100;
        atk = 50;
        dodge = 0;
        healthPotAmount = 2;
        upgradeShards = 0;
        armorInventory = new Armor[1];
        weaponInventory = new Weapon[1];
        armor = new Armor(150, 25, 10, "Mercenary Armor");
        addArmor(armor);
        weapon = new Weapon("Iron Sword", 75, 20);
        addWeapon(weapon);
    }

    public String getName(){
        return name;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getGold(){
        return gold;
    }

    public Armor getArmor(){
        return armor;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Armor[] getArmorInventory(){
        return armorInventory;
    }

    public Weapon[] getWeaponInventory(){
        return weaponInventory;
    }

    public void addGold(int gold){
        this.gold += gold;
    }
    
    public void addArmor(Armor newArmor){
        Armor[] newArmorInventory = new Armor[armorInventory.length + 1];
        int idx = 0;
        for (Armor armor: armorInventory){
            newArmorInventory[idx] = armor;
            idx++;
        }
        newArmorInventory[newArmorInventory.length - 1] = newArmor;
        armorInventory = newArmorInventory;
    }

    public void addWeapon(Weapon newWeapon){
        Weapon[] newWeaponInventory = new Weapon[weaponInventory.length + 1];
        int idx = 0;
        for (Weapon weapon: weaponInventory){
            newWeaponInventory[idx] = weapon;
            idx++;
        }
        newWeaponInventory[newWeaponInventory.length - 1] = newWeapon;
        weaponInventory = newWeaponInventory;
    }

    public void printStats(){
        System.out.print(name);
        System.out.println("Attack (⚔): " + atk);
        System.out.println("Health (♥): " + health);
        System.out.println("Gold ($): " + gold);
        System.out.println("Burning (\uD83D\uDD25): " + burnDuration + " turns");
    }

    public void printWeaponInventory(){
        int idx = 1;
        for (Weapon weapon: weaponInventory){
            System.out.print(idx + ". " + weapon.getName() + "[Level " + weapon.getLevel() + "]");
            if (weapon == this.weapon){
                System.out.print(" << Currently equipped >>");
            }
            System.out.println();
            idx++;
        }
    }

    public void printArmorInventory(){
        int idx = 1;
        for (Armor armor: armorInventory){
            System.out.print(idx + ". " + armor.getName() + "[Level " + armor.getLevel() + "]");
            if (armor == this.armor){
                System.out.print(" << Currently equipped >>");
            }
            System.out.println();
            idx++;
        }
        System.out.println(armorInventory.length + ". Exit");
        System.out.println();
    }

    public Weapon selectWeapon(){
        System.out.println("Select a weapon: ");
        int choice = scan.nextInt();

        return weaponInventory[choice];
    }

    public Armor selectArmor(){
        System.out.println("Select armor: ");
        int choice = scan.nextInt();

        return armorInventory[choice];
    }

    public void changeWeapon(Weapon newWeapon){
        int [] oldStats = weapon.getStats();
        int [] newStats = newWeapon.getStats();
        changeCombatStats(0, -oldStats[0], -oldStats[1]);
        changeCombatStats(0, newStats[0], newStats[1]);
        weapon = newWeapon;
    }

    public void changeArmor(Armor newArmor){
        int [] oldStats = armor.getStats();
        int [] newStats = newArmor.getStats();
        changeCombatStats(-oldStats[0], -oldStats[1], -oldStats[2]);
        changeCombatStats(newStats[0], newStats[1], newStats[2]);
        armor = newArmor;
    }

    public void changeCombatStats(int health, int atk, int dodge){
        this.atk += atk;
        this.dodge += dodge;
        this.health += health;
        maxHealth += health;
    }

    public int swordSwing(){
        double random = (Math.random() + 0.76) * atk;
        return (int) random;
    }

    public int swordCleave(){
        DragonSlayer.skillPoints -= 2;
        double random = (Math.random() + 1.01) * atk;
        return (int) random;
    }

    public int cascadingLance(){
        DragonSlayer.skillPoints -= 4;
        double random = (Math.random() + 2.01) * atk;
        return (int) random;
    }

    public void act(Room room){
        int choice = 0;
        while (choice < 1 || choice > 4) {
            System.out.println("\nChoose your move: ");
            System.out.println("1. Sword Swing (0 SP): Deals damage to a single target");
            System.out.println("2. Cleave (2 SP): Deals damage to a target and adjacent ones");
            System.out.println("3. Cascading Lance (4 SP): Deals heavy damage to all targets\n");
            System.out.println("4. Consume health pot (you have " + healthPotAmount + ")");
            choice = scan.nextInt();

            if (choice < 1 || choice > 4 || (choice == 4 && healthPotAmount == 0)){
                System.out.println("Invalid choice, please try again");
            }
        }
        if (choice == 1){
            attack(room.getMobs(), 1);
        } else if (choice == 2){
            attack(room.getMobs(), 2);
        } else if (choice == 3){
            attack(room.getMobs(), 3);
        } else {
            health += maxHealth * 0.75;
            healthPotAmount--;
        }
    }

    public void attack(Dragon[] mobs, int move){
        int damage = 0;
        if (move != 3) {
            int target = target(mobs);
            if (move == 1){
                damage = swordSwing();
                System.out.println("You hit " + mobs[target].getType()+ " for " + damage + " damage!");
                mobs[target].damage(damage);
                DragonSlayer.skillPoints++;
            } else {
                damage = swordCleave();
                if (target == 0 || target == mobs.length - 1){
                    if (target == 0) {
                        System.out.println("You hit " + mobs[target].getType()+ " and " + mobs[target + 1].getType()+ " for " + damage + " damage!");
                        mobs[target].damage(damage);
                        mobs[target + 1].damage(damage);
                    } else{
                        System.out.println("You hit " + mobs[target].getType()+ " and " + mobs[target - 1].getType()+ " for " + damage + " damage!");
                        mobs[target].damage(damage);
                        mobs[target - 1].damage(damage);
                    }
                } else {
                    System.out.println("You hit " + mobs[target - 1].getType()+ ", " + mobs[target].getType()+ " and " + mobs[target + 1].getType()+ " for " + damage + " damage!");
                    mobs[target - 1].damage(damage);
                    mobs[target].damage(damage);
                    mobs[target + 1].damage(damage);
                }
                DragonSlayer.skillPoints -= 2;
            }
        } else {
            damage = cascadingLance();
            System.out.println("You hit all targets for " + damage + " damage!");
            for (Dragon dragon: mobs){
                dragon.damage(damage);
            }
            DragonSlayer.skillPoints -= 4;
        }

        for (Dragon dragon: mobs){
            if (dragon.getHealth() < 0){
                dragon.death(mobs, this);
            }
        }
        DragonSlayer.skillPoints++;
    }

    public int target(Dragon[] mobs){
        System.out.println("Pick a target (number): ");
        int choice = scan.nextInt();
        if (choice < 1 || choice > mobs.length - 1){
            System.out.println("Invalid option, try again: ");
            choice = scan.nextInt();
        }
        return choice - 1;
    }

    public void damage(int damage){
        health -= damage;
    }

    public void burnDamage(){
        health -= burnDamage;
        burnDuration--;
    }

    public boolean dodge(){
        int random = (int)(Math.random() * 100) + 1;
        if (dodge > random){
            return true;
        }
        return false;
    }
}
