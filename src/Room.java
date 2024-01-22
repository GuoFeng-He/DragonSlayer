public class Room {
    public static int roomNum = 0;
    private boolean hasHealthPot;
    private String roomName;
    private String entranceText;
    private int numberOfMobs;
    private int dragonLevels;
    private Dragon[] mobs;
    private boolean cleared;

    public Room(){;
        roomNum += 1;
        setRoom();
        if (Math.round(Math.random() * 2) + 1 == 1){
            hasHealthPot = true;
        } else {
            hasHealthPot = false;
        }

        mobs = new Dragon[numberOfMobs];
        for (int i = 0; i < numberOfMobs; i++) {
            Dragon dragon = new Dragon(Dragon.randomType(), dragonLevels);
            mobs[i] = dragon;
        }
    }

    public Dragon[] getMobs(){
        return mobs;
    }

    public boolean isCleared(){
        return cleared;
    }

    public void setRoom(){
        numberOfMobs = 0;
        cleared = false;
        if (DragonSlayer.difficultyScale == 1) {
            entranceText = "";
            if (roomNum == 1) {
                roomName = "Entrance (Room 1 / 5)";
                entranceText = "The maw of the dragon's den yawns before you.";
                numberOfMobs = 1;
                dragonLevels = 1;
            } else if (roomNum == 2) {
                roomName = "Corridor (Room 2 / 5)";
                entranceText = "A long, dark corridor barely lit up by your torch";
                numberOfMobs = 2;
                dragonLevels = 1;
            } else if (roomNum == 3) {
                roomName = "Atrium (Room 3 / 5)";
                entranceText = "A beautifully lit atrium full of precious jewels";
                numberOfMobs = 2;
                dragonLevels = 2;
            } else if (roomNum == 4) {
                roomName = "Gateway (Room 4 / 5)";
                entranceText = "A big door with massive dragon statues staring you down. The heat is becoming unbearable.";
                numberOfMobs = 2;
                dragonLevels = 3;
            } else if (roomNum == 5) {
                roomName = "Treasure Room (Room 5 / 5)";
                entranceText = "A room filled to the brim with treasure. A massive dragon stares you down."; // do this
                numberOfMobs = 3;
                dragonLevels = 3;
            }
        } else {
            if (roomNum == 1) {
                roomName = "The Eerie Entrance (Room 1 / 5)";
                entranceText = "The entrance of the den is in ruins. Something is not right.";
                numberOfMobs = 2;
                dragonLevels = 2;
            } else if (roomNum == 2) {
                roomName = "The Desolate Depths (Room 2 / 5)";
                entranceText = "Although eerily empty, you feel an ominous presence watching you.";
                numberOfMobs = 3;
                dragonLevels = 2;
            } else if (roomNum == 3) {
                roomName = "The Hopeless Hatchery (Room 3 / 5)";
                entranceText = "An empty room full of cracked dragon eggs. You swear roars of anguish could still be heard.";
                numberOfMobs = 4;
                dragonLevels = 2;
            } else if (roomNum == 4) {
                roomName = "The Door to Damnation (Room 4 / 5)";
                entranceText = "Turn back. Nothing past this door is worth dying for.";
                numberOfMobs = 4;
                dragonLevels = 3;
            } else if (roomNum == 5){
                roomName = "The Treasure Room? (Room 5 / 5)"; // do this
                entranceText = "It's time to end this.";
                numberOfMobs = 5;
                dragonLevels = 4;
            }
        }
    }

    public void printRoomInfo(){
        System.out.println("\n\n" + roomName);
        System.out.println(entranceText);
        System.out.println();
    }

    public void printMobs(){
        for (int i = 0; i < mobs.length; i++){
            if (!(mobs[i] == null)){
                System.out.println((i + 1) + ". [Level " + mobs[i].getLevel() + "]" +  mobs[i].getType());
                System.out.println("Health (♥): " + mobs[i].getHealth());
                System.out.println("Attack (⚔): " + mobs[i].getAtk() + "\n");
            }
        }
    }

    public void searchRoom(){
        if (hasHealthPot){
            System.out.println("You found a health pot!");
            Player.healthPotAmount++;
            hasHealthPot = false;

        } else {
            System.out.println("You found nothing.");
        }
    }

    public boolean allNull(){
        for (int i = 0; i < mobs.length; i++){
            if (mobs[i] != null){
                return false;
            }
        }
        return true;
    }

    public void cleared(){
        if (allNull()){
            cleared = true;
        }
    }

    public void changeMobs(Dragon[] newMobs){
        mobs = newMobs;
    }

}
