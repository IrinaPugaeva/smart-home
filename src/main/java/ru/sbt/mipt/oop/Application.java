package ru.sbt.mipt.oop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {

        FileReader reader = new HomeInternetFileReader();
        SmartHome smartHome = new SmartHome();
        Door door = new Door(true, "1");
        Light light = new Light("1", true);
        Door hall_door = new Door(true, "2");
        Light hall_light = new Light("2", true);
        smartHome.addRoom(new Room(Arrays.asList(light), Arrays.asList(door), "room"));
        smartHome.addRoom(new Room(Arrays.asList(hall_light), Arrays.asList(hall_door), "hall"));


        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        SensorEventObserver sensorEventObserver = new SensorEventObserver();
        configurateHandlers(sensorEventObserver);
        sensorEventObserver.runEventCycle();}

    private static void configurateHandlers(SensorEventObserver observer) {
        Collection<EventHandler> handlers = new ArrayList<>();
        handlers.add(new LightEventHandler());
        handlers.add(new DoorEventHandler());
        handlers.add(new SimpleHandlerDecorator(new SimpleScenarioHandler()));
    }
/*
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler: handlers) {
                handler.handle(smartHome, event);
            }
            event = getNextSensorEvent();
        }*/


    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

}
