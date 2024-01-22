public class Weapon {
    private String name;
    private int atk;
    private int dodge;
    private int level;

    public Weapon(String name, int atk, int dodge){
        this.name = name;
        this.atk = atk;
        this.dodge = dodge;
        level = 1;
    }

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

    public void upgrade(){
        if (level < 15) {
            int stat = (int) (Math.random() * 2) + 1;
            level++;
            System.out.println("Successfully upgraded " + name + " to level " + level + "!");
             if (stat == 1) {
                System.out.println("+" + (int)(atk * 1.03) + " ATK!");
                atk *= 1.03;
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
        System.out.println("Attack (⚔): " + atk);
        System.out.println("Dodge (\uD83D\uDC5F): " + dodge);
    }
}
