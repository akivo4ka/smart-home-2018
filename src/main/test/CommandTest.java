import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.SmartHomeLoader;
import ru.sbt.mipt.oop.alarmsystem.AlarmSystemStateEnum;
import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.processors.DoorIterator;
import ru.sbt.mipt.oop.processors.LightIterator;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

public class CommandTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void testCommandHallDoorClose() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Command closeHallDoorTest = new CloseHallDoorCommand(smartHome);
        closeHallDoorTest.execute();
        DoorIterator doorIterator = new DoorIterator(smartHome);
        for (Door door: doorIterator) {
            if (doorIterator.getCurrentRoom().getName().equals("hall")) {
                Assert.assertFalse(door.isOpen());
            }
        }
    }

    @Test
    public void testSwitchAllLight() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Command turnOnAllLightTest = new TurnOnAllLightsCommand(smartHome);
        turnOnAllLightTest.execute();
        LightIterator lightIterator = new LightIterator(smartHome);
        for (Light light: lightIterator) {
            Assert.assertTrue(light.isOn());
        }
        Command turnOffAllLightTest = new TurnOffAllLightsCommand(smartHome);
        turnOffAllLightTest.execute();
        for (Light light: lightIterator) {
            Assert.assertFalse(light.isOn());
        }
    }

    @Test
    public void testAlarmSystem() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Command alarmSystemTest = new ActivateAlaramSystemCommand(smartHome);
        alarmSystemTest.execute();
        SensorEvent alarmSensorEvent = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "qwerty");
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        alarmEventProcessor.process(alarmSensorEvent);
        Assert.assertEquals(smartHome.getAlarmSystem().getSystemState(), AlarmSystemStateEnum.ALARM);
    }

}
