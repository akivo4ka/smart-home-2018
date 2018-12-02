package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {

    private String remoteControllerId;
    private Map<String, Command> remoteControllerButtonCommandMap = new HashMap<>();

    public RemoteController(String remoteControllerId) {
        this.remoteControllerId = remoteControllerId;
    }

    public String getRemoteControllerId() {
        return remoteControllerId;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (remoteControllerButtonCommandMap.containsKey(buttonCode)) {
            remoteControllerButtonCommandMap.get(buttonCode).execute();
        }
    }

    public void addNewButtonCommand(Command command, String buttoncCode) {
        remoteControllerButtonCommandMap.put(buttoncCode, command);
    }
}
