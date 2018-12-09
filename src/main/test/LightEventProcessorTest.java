import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.Room;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.phrases.SmartHomePhrases;
import ru.sbt.mipt.oop.phrases.SmartHomePhrasesLoader;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class LightEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processLightEventTest() throws Exception {
        SmartHomePhrases smartHomePhrases = (new SmartHomePhrasesLoader()).loadSmartHomePhrases();
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");
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
