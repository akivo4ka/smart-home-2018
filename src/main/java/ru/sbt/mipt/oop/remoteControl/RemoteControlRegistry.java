package ru.sbt.mipt.oop.remoteControl;

import java.util.ArrayList;
import java.util.List;

public class RemoteControlRegistry {

    List<RemoteControl> remoteControlList;

    public RemoteControlRegistry() {
        remoteControlList = new ArrayList<>();
    }

    public void registerRemoteControl(RemoteControl remoteControl) {
        remoteControlList.add(remoteControl);
    }
}
