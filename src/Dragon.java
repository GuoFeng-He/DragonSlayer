public class Dragon {
    private String type;
    private int level;
    private int health;
    private int maxHealth;
    private int atk;

    public Dragon(String type, int level){
        this.type = type;
        this.level = level;
        getStats();
    }

    private void getStats(){
        health = 100 * (int) Math.pow(3, level);
        atk = (int)(10 * Math.pow(3, level / 2.0)); // placeholder stats
    }

    public int claw(){
        return atk;
    }

    /* phoenix dragon */
    // revives at 50% HP when it takes a fatal hit
    public void lastBreath(){
        health = maxHealth / 2;
        atk *= 1.15;
    }

    // single target attack that has a chance to burn enemies
    public int everlastingBurn() {
        return (int)(atk * 1.25);
    }

    /* Dragon of Vitality */
    public double heal(){
        return maxHealth * Math.random() / 4;
    }

    public double bigHeal(){ // aoe
        return maxHealth * Math.random() / 2;
    }

    /* Berserker Dragon */
    public void powerAmplification(double multiplier){
        atk *= multiplier;
    }

    // multi hit attack (3rd element is damage)
    public int[] berserk(){
        int[] targets = new int[3];
        targets[0] = (int)(Math.random() * 2);
        targets[1] = (int)(Math.random() * 2);
        targets[2] = atk;
        return targets;
    }

    /* Corrupted Dragon <Master Mode Only>
    * Copies abilities of other dragon types*/


}
