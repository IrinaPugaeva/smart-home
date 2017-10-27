package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventHandler {
    void handle(SmartHome home, SensorEvent event);
}
