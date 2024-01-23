public class Dragon {
    private String type;
    private int level;
    private int health;
    private int maxHealth;
    private int atk;
    private boolean revive;

    public Dragon(String type, int level){
        this.type = type;
        this.level = level;
        setStats();
    }

    private void setStats(){
        health = 100 * 3 * level * DragonSlayer.difficultyScale;
        if (type.equals("Dragon of Vitality")){
            health *= 1.5;
        }
        atk = (int)(10 * level * 1.5 * DragonSlayer.difficultyScale); // placeholder stats
        if (type.equals("Berserker Dragon")){
            atk *= 1.5;
        }
    }

    public String getType(){
        return type;
    }

    public int getHealth(){
        return health;
    }

    public int getAtk(){
        return atk;
    }

    public int getLevel(){
        return level;
    }

    public static String randomType(){
        int random = (int)(Math.random() * 4);
        String type = "";
        if (random == 1){
            type = "Dragon";
        } else if (random == 2){
            type = "Phoenix Dragon";
        } else if (random == 3){
            type = "Dragon of Vitality";
        } else {
            type = "Berserker Dragon";
        }
        return type;
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

    // attack that has a chance to burn enemies
    public int everlastingBurn() {
        return (int)(atk * 1.25);
    }

    /* Dragon of Vitality */
    public double heal(){
        return maxHealth * (Math.random() + 0.3);
    }

    public double bigHeal(){ // aoe
        return maxHealth * (Math.random() + 0.75);
    }

    /* Berserker Dragon */
    public void powerAmplification(double multiplier){
        atk *= multiplier;
    }

    // massive damage
    public int berserk(){
        return (int)(atk * 2);
    }

    public void ability(Player player, Dragon[] mobs){
        int random = (int)(Math.random() * 3);
        int damage = 0;
        if (random == 1 || (type.equals("Phoenix Dragon") && random == 2) || type.equals("Dragon")){
            damage = claw();
            if (player.dodge()){
                System.out.println("You dodged the " + type + "'s claw!");
            } else {
                System.out.println("The " + type + " hit you for " + damage);
                player.damage(damage);
            }
        } else if (random == 2){
            if (type.equals("Dragon of Vitality")){
                damage = (int)heal();
                System.out.println("The Dragon of Vitality healed all its allies for " + damage + " health");
                for (Dragon dragon: mobs){
                    if (dragon != null) {
                        dragon.damage(-damage);
                    }
                }
            } else if (type.equals("Berserker Dragon")){
                System.out.println("The Berserker Dragon's rage increased!");
                System.out.println("Its attack has increased!");
                powerAmplification(1.5);
            }
        } else if (random == 3){
            if (type.equals("Dragon of Vitality")){
                damage = (int)(bigHeal());
                System.out.println("The Dragon of Vitality healed all its allies for a massive " + damage + " health!");
                for (Dragon dragon: mobs){
                    dragon.damage(-damage);
                }
            } else if (type.equals("Berserker Dragon")){
                damage = berserk();
                System.out.println("The Berserker Dragon gave you a lobotomy!");
                if (player.dodge()){
                    System.out.println("Fortunately, you dodged it!");
                } else {
                    System.out.println("You took " + damage + " damage");
                    player.damage(damage);
                }
            } else if (type.equals("Phoenix Dragon")) {
                damage = everlastingBurn();
                System.out.println("The Phoenix Dragon scorched you with its flames!");
                if (player.dodge()){
                    System.out.println("However, its flames gave it a coughing fit before they could reach you!");
                } else {
                    System.out.println("You took " + damage + " damage and will be taking " + (int) (damage * 1.75) + " damage at the start of your next 2 turns!");
                    player.damage(damage);
                    Player.burnDuration += 2;
                    Player.burnDamage = (int)(damage * 1.75);
                }
            }
        }
    }

    public Dragon[] death(Dragon[] mobs, Player player){
        System.out.println(type + " down!");
        Dragon[] newMobs = new Dragon[mobs.length];

        int idx = 0;
        for (int i = 0; i < mobs.length; i++){
            if (!(mobs[i] == this)){
                newMobs[idx] = mobs[i];
                idx++;
            }
        }
        loot(player);

        return newMobs;
    }

    public void loot(Player player){
        int random = (int)(Math.random() * 7);
        if (random == 0 || random == 1){
            System.out.println("The dragon sadly dropped nothing");
        } else if (random == 2 || random == 3){
            System.out.println("The dragon somehow had two health potions on it");
            Player.healthPotAmount += 2;
        } else if (random == 4 || random == 5){
            System.out.println("The dragon dropped an upgrade shard");
            Player.upgradeShards++;
        } else if (random == 6){
            int armor = (int)(Math.random() * 3);
            Armor newArmor;
            if (armor == 0){
                newArmor = new Armor(400, 100,15, "Dragon Slayer Armor");
            } else if (armor == 1){
                newArmor = new Armor(200, 50, 35, "Armor of Hermes");
            } else{
                newArmor = new Armor(600, 25, 10, "Goldor's Armor");
            }
            player.addArmor(newArmor);
            System.out.println("You got a " + newArmor.getName() + "!");
        } else if (random == 7){
            int weapon = (int)(Math.random() * 3);
            Weapon newWeapon;
            if (weapon == 0){
                newWeapon = new Weapon("Dragon's Bane", 300, 20);
            } else if (weapon == 1){
                newWeapon = new Weapon("Decent Sword", 150, 25);
            } else {
                newWeapon = new Weapon("Blood Blade", 200, 30);
            }
            player.addWeapon(newWeapon);
            System.out.println("You got a " + newWeapon.getName() + "!");
        }
        System.out.println("The dragon gave you 10 gold!");
        System.out.println();
        player.addGold(10);
    }

    public void damage(int damage){
        health -= damage;
    }
}
