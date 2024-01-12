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
            if (roomNum == 1) {
                roomName = "Entrance";
                entranceText += "The maw of the dragon's den yawns before you.";
            } else if (roomNum == 2) {
                roomName = "Corridor";
                entranceText += "A long, dark corridor barely lit up by your torch";
            } else if (roomNum == 3) {
                roomName = "Atrium";
                entranceText += "A beautifully lit atrium full of precious jewels";
            } else if (roomNum == 4) {
                roomName = "Gateway";
                entranceText += "A big door with massive dragon statues staring you down. The heat is becoming unbearable.";
            } else if (roomNum == 5) {
                roomName = "Treasure Room";
                entranceText += ""; // do this
            }
        } else {
            if (roomNum == 1) {
                roomName = "The Eerie Entrance";
                entranceText += "The entrance of the den is in ruins. Something is not right.";
            } else if (roomNum == 2) {
                roomName = "The Desolate Depths";
                entranceText += "Although eerily empty, you feel an ominous presence watching you.";
            } else if (roomNum == 3) {
                roomName = "The Hopeless Hatchery";
                entranceText += "An empty room full of cracked dragon eggs. You swear roars of anguish could still be heard.";
            } else if (roomNum == 4) {
                roomName = "The Door to Damnation";
                entranceText += "Turn back. Nothing past this door is worth dying for.";
            } else if (roomNum == 5){
                roomName = ""; // do this
            }

        }
    }

}
