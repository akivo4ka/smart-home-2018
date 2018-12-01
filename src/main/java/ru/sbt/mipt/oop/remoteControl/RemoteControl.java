package ru.sbt.mipt.oop.remoteControl;

public interface RemoteControl {

    void onButtonPressed(String buttonCode); // код нажатой кнопки: “A”, “B”, “C”, “D”, “1”, “2”, “3”, “4”

}
