package ru.sbt.mipt.oop.remotecontrol;

public interface RemoteControl {

    void onButtonPressed(String buttonCode); // код нажатой кнопки: “A”, “B”, “C”, “D”, “1”, “2”, “3”, “4”

}
