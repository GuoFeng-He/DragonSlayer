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
        if (type.equals("Phoenix Dragon")){
            revive = true;
        }
        maxHealth = health;
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
        atk *= 1.5;
    }

    // attack that has a chance to burn enemies
    public int everlastingBurn() {
        return (int)(atk * 1.25);
    }

    /* Dragon of Vitality */
    public double heal(){
        return maxHealth * 0.1;
    }

    public double bigHeal(){
        return maxHealth * 0.2;
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
        int random = (int)(Math.random() * 3) + 1;
        int damage;
        if (random == 1 || (type.equals("Phoenix Dragon") && random == 2) || type.equals("Dragon")){
            damage = claw();
            if (player.dodge()){
                System.out.println(Color.YELLOW + "You dodged the " + type + "'s claw!" + Color.RESET);
            } else {
                System.out.println(Color.RED + "The " + type + " hit you for " + damage + " damage" + Color.RESET);
                player.damage(damage);
            }
        } else if (random == 2){
            if (type.equals("Dragon of Vitality")){
                damage = (int)heal();
                System.out.println(Color.GREEN + "The Dragon of Vitality healed all its allies for " + damage + " health" + Color.RESET);
                for (Dragon dragon: mobs){
                    if (dragon != null) {
                        dragon.damage(-damage);
                    }
                }
            } else if (type.equals("Berserker Dragon")){
                System.out.println(Color.RED + "The Berserker Dragon's rage increased!");
                System.out.println("Its attack has increased!" + Color.RESET);
                powerAmplification(1.5);
            }
        } else if (random == 3){
            if (type.equals("Dragon of Vitality")){
                damage = (int)(bigHeal());
                System.out.println(Color.GREEN + "The Dragon of Vitality healed all its allies for a massive " + damage + " health!" + Color.RESET);
                for (Dragon dragon: mobs){
                    if (dragon != null) {
                        dragon.damage(-damage);
                    }
                }
            } else if (type.equals("Berserker Dragon")){
                damage = berserk();
                System.out.println(Color.RED + "The Berserker Dragon gave you a lobotomy!" + Color.RESET);
                if (player.dodge()){
                    System.out.println(Color.YELLOW + "Fortunately, you dodged it!" + Color.RESET);
                } else {
                    System.out.println(Color.RED + "You took " + damage + " damage" + Color.RESET);
                    player.damage(damage);
                }
            } else if (type.equals("Phoenix Dragon")) {
                damage = everlastingBurn();
                System.out.println(Color.RED + "The Phoenix Dragon scorched you with its flames!" + Color.RESET);
                if (player.dodge()){
                    System.out.println(Color.YELLOW + "However, its flames gave it a coughing fit before they could reach you!" + Color.RESET);
                } else {
                    System.out.println(Color.RED + "You took " + damage + " damage and will be taking " + (int) (damage * 1.75) + " damage at the start of your next 2 turns!" + Color.RESET);
                    player.damage(damage);
                    Player.burnDuration += 2;
                    Player.burnDamage = (int)(damage * 1.75);
                }
            }
        }
    }

    public Dragon[] death(Dragon[] mobs, Player player){
        if (!revive) {
            System.out.println(Color.CYAN + type + " down!" + Color.RESET);
            Dragon[] newMobs = new Dragon[mobs.length];

            int idx = 0;
            for (int i = 0; i < mobs.length; i++) {
                if (!(mobs[i] == this) && (mobs[i] != null && mobs[i].health > 0)) {
                    newMobs[idx] = mobs[i];
                    idx++;
                }
            }
            loot(player);

            return newMobs;
        } else {
            System.out.println(Color.GREEN + "The Phoenix Dragon revived itself to half health! Its attack increased as well." + Color.RESET);
            revive = false;
            lastBreath();
            return mobs;
        }
    }

    public void loot(Player player){
        int random = (int)(Math.random() * 8);
        if (random == 0 || random == 1){
            System.out.println(Color.YELLOW + "The dragon sadly dropped nothing");
        } else if (random == 2 || random == 3){
            System.out.println(Color.YELLOW + "The dragon somehow had two health potions on it");
            Player.healthPotAmount += 2;
        } else if (random == 4 || random == 5){
            System.out.println(Color.YELLOW + "The dragon dropped an upgrade shard");
            Player.upgradeShards++;
        } else if (random == 6){
            int armor = (int)(Math.random() * 3);
            Armor newArmor;
            if (armor == 0){
                newArmor = new Armor(600, 200,20, "Dragon Slayer Armor");
            } else if (armor == 1){
                newArmor = new Armor(300, 100, 40, "Armor of Hermes");
            } else{
                newArmor = new Armor(1000, 50, 10, "Goldor's Armor");
            }
            player.addArmor(newArmor);
            System.out.println(Color.BLUE + "You got a " + newArmor.getName() + "!");
        } else if (random == 7){
            int weapon = (int)(Math.random() * 3);
            Weapon newWeapon;
            if (weapon == 0){
                newWeapon = new Weapon("Dragon's Bane", 500, 20);
            } else if (weapon == 1){
                newWeapon = new Weapon("Decent Sword", 200, 25);
            } else {
                newWeapon = new Weapon("Blood Blade", 350, 30);
            }
            player.addWeapon(newWeapon);
            System.out.println(Color.BLUE + "You got a " + newWeapon.getName() + "!");
        }
        if (DragonSlayer.difficultyScale == 1){
            System.out.println(Color.RESET + Color.YELLOW + "The dragon gave you 10 gold!" + Color.RESET);
            player.addGold(10);
        } else {
            System.out.println(Color.RESET + Color.YELLOW + "The dragon gave you 20 gold!" + Color.RESET);
            player.addGold(20);
        }
    }

    public void damage(int damage){
        health -= damage;
    }
}
