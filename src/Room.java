public class Room {
    private static int roomNum = 0;
    private boolean hasHealthPot;
    private String roomName;
    private String entranceText;
    private int numberOfMobs;

    public Room(){;
        roomNum += 1;
        setRoomName();
        if (Math.round(Math.random() * 2) + 1 == 1){
            hasHealthPot = true;
        } else {
            hasHealthPot = false;
        }

    }

    public void setRoomName(){
        if (DragonSlayer.difficultyScale == 1) {
            entranceText = "";
            if (roomNum == 1) {
                roomName = "Entrance (Room 1 / 5)";
                entranceText = "The maw of the dragon's den yawns before you.";
            } else if (roomNum == 2) {
                roomName = "Corridor (Room 2 / 5)";
                entranceText = "A long, dark corridor barely lit up by your torch";
            } else if (roomNum == 3) {
                roomName = "Atrium (Room 3 / 5)";
                entranceText = "A beautifully lit atrium full of precious jewels";
            } else if (roomNum == 4) {
                roomName = "Gateway (Room 4 / 5)";
                entranceText = "A big door with massive dragon statues staring you down. The heat is becoming unbearable.";
            } else if (roomNum == 5) {
                roomName = "Treasure Room (Room 5 / 5)";
                entranceText = "A room filled to the brim with treasure. A massive dragon stares you down."; // do this
            }
        } else {
            if (roomNum == 1) {
                roomName = "The Eerie Entrance (Room 1 / 5)";
                entranceText = "The entrance of the den is in ruins. Something is not right.";
            } else if (roomNum == 2) {
                roomName = "The Desolate Depths (Room 2 / 5)";
                entranceText = "Although eerily empty, you feel an ominous presence watching you.";
            } else if (roomNum == 3) {
                roomName = "The Hopeless Hatchery (Room 3 / 5)";
                entranceText = "An empty room full of cracked dragon eggs. You swear roars of anguish could still be heard.";
            } else if (roomNum == 4) {
                roomName = "The Door to Damnation (Room 4 / 5)";
                entranceText = "Turn back. Nothing past this door is worth dying for.";
            } else if (roomNum == 5){
                roomName = "The Treasure Room? (Room 5 / 5)"; // do this
                entranceText = "Reality starts warping around you. It's time to end this.";
            }
        }
        entranceText += "\n";
    }

    public void printRoomInfo(){
        System.out.println(roomName);
        System.out.println(entranceText);
    }

}
