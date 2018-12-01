import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class LightEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processLightEventTest() throws Exception {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        String lightId = "1";
        SensorEvent lightOnEvent = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
        LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
        lightEventProcessor.process(lightOnEvent);

        for (Room room: smartHome.getRooms()) {
            for (Light light: room.getLights()) {
                if (light.getId().equals(lightId)) {
                    Assert.assertTrue(light.isOn());
                    break;
                }
            }
        }

    }

}
