import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.HomeEventsObserver;
import ru.sbt.mipt.oop.sensoreventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.alarmsystem.AlarmSystem;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.sensoreventprovider.MySensorEventProvider;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class HomeEventsObserverTest {

    private SmartHome smartHome;

    @Before
    public void setSmartHome() throws IOException {
        smartHome = (new FileSmartHomeLoader()).loadSmartHome();
        smartHome.setAlarmSystem(new AlarmSystem());
    }

    @Test
    public void homeEventsObserverTest() {
        SensorEvent sensorEvent = mock(SensorEvent.class);
        List<SensorEvent> sensorEventList = new ArrayList<>();
        sensorEventList.add(sensorEvent);
        SensorEventProvider sensorEventProvider = new MySensorEventProvider(sensorEventList);

        EventProcessor eventProcessor = mock(EventProcessor.class);
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        eventProcessorList.add(eventProcessor);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(eventProcessorList, sensorEventProvider);
        homeEventsObserver.start();

        verify(eventProcessor).process(sensorEvent);
    }
}
