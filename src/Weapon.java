public class Weapon {
    private String name;
    private int atk;
    private int dodge;
    private int level;

    // creates weapon with its name and base attack/dodge chance. all weapons start at level 1.
    public Weapon(String name, int atk, int dodge){
        this.name = name;
        this.atk = atk;
        this.dodge = dodge;
        level = 1;
    }

    // getters
    public int[] getStats(){
        int[] stats = new int[2];
        stats[0] = atk;
        stats[1] = dodge;
        return stats;
    }

    public String getName(){
        return name;
    }
    public int getLevel(){
        return level;
    }

    /* method used for upgrading weapons.
    * A random stat is chosen everytime to be upgraded (requires one upgrade shard per upgrade)
    */
    public void upgrade(Player player){
        if (level < 15) {
            int stat = (int) (Math.random() * 2) + 1;
            level++;
            System.out.println("Successfully upgraded " + name + " to level " + level + "!");
             if (stat == 1) {
                System.out.println("+" + (int)(atk * 0.2) + " ATK!");
                atk *= 1.2;
                 player.changeCombatStats(0, (int)(atk * 0.2), 0);
            } else {
                System.out.println("+1% chance to dodge!");
                dodge++;
                 player.changeCombatStats(0, 0, 1);
            }
            Player.upgradeShards--;
        } else {
            System.out.println("Your armor has already been maxed out!");
        }
    }

    // prints weapon stats
    public void printStats(){
        System.out.println(name + " [Level " + level + "]");
        System.out.println("Attack (⚔): " + atk);
        System.out.println("Dodge (\uD83D\uDC5F): " + dodge);
    }
}
