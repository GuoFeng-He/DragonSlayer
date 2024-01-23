public class Armor {
    private int health;
    private int atk;
    private int dodge;
    private String name;
    private int level;

    public Armor(int health, int atk, int dodge, String name){
        this.health = health;
        this.atk = atk;
        this.dodge = dodge;
        this.name = name;
        level = 1;
    }

    public int[] getStats(){
        int[] stats = new int[4];
        stats[0] = health;
        stats[1] = atk;
        stats[2] = dodge;
        stats[3] = level;
        return stats;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    // upgrading armor
    public void upgrade(){
        if (level < 15) {
            int stat = (int) (Math.random() * 3) + 1;
            level++;
            System.out.println("Successfully upgraded " + name + " to level " + level + "!");
            if (stat == 1) {

                System.out.println("+" + (int)(health * 1.05) + " health!");
                health = (int)(health * 1.05);
            } else if (stat == 2) {
                System.out.println("+" + (int)(atk * 1.03) + " ATK!");
                atk = (int)(atk * 1.03);
            } else {
                System.out.println("+ 1% chance to dodge!");
                dodge++;
            }
            Player.upgradeShards--;
        } else {
            System.out.println("Your armor has already been maxed out!");
        }
    }

    public void printStats(){
        System.out.println(name + " [Level " + level + "]");
        System.out.println("Health (♥): " + health);
        System.out.println("Attack (⚔): " + atk);
        System.out.println("Dodge (\uD83D\uDC5F): " + dodge);
    }
}
