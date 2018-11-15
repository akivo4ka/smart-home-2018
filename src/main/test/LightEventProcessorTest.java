import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;
import java.util.Arrays;

public class LightEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processLightEventTest() throws Exception {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        String lightId = "1";
        SensorEvent lightOnEvent = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
        LightEventProcessor.processLightEvent(smartHome, lightOnEvent);


        for (Room room: smartHome.getRooms()) {
            for (Light light: room.getLights()) {
                if (light.getId().equals(lightId)) {
                    Assert.assertEquals(light.isOn(), true);
                    break;
                }

            }

        }


    }

}
