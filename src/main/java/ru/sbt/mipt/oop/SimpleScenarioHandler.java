package ru.sbt.mipt.oop;

public class SimpleScenarioHandler implements EventHandler {
    @Override
    public void handle(SmartHome home, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED || event.getType() == SensorEventType.DOOR_OPEN) {
            for (Room room : home.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                        if (event.getType() == SensorEventType.DOOR_CLOSED) {
                            applyToAllDoors(home, false);
                            applyToAllLights(home, false);
                        } else {
                            applyToAllDoors(home, true);
                            applyToAllLights(home, true);
                        }
                    }
                }
            }
        }
    }

    private void applyToAllLights(SmartHome home, boolean operation) {
        for (Room room: home.getRooms()) {
            for (Light light: room.getLights()) {
                light.setOn(operation);
            }
        }
    }

    private void applyToAllDoors(SmartHome home, boolean operation) {
        for (Room room: home.getRooms()) {
            for (Door door: room.getDoors()) {
                door.setOpen(operation);
            }
        }
        System.out.println("All doors are closed now");
    }
}
