import org.junit.Test;
import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class ActionTest {

    @Test
    public void actionableCompositeTest() {
        List<Light> lights1 = Arrays.asList(new Light("1", false), new Light("2", true));
        List<Door> doors1 = Collections.singletonList(new Door(false, "1"));
        Room kitchen = new Room(lights1, doors1, "kitchen");

        List<Light> lights2 = Collections.singletonList(new Light("3", true));
        List<Door> doors2 = Collections.singletonList(new Door(false, "2"));
        Room bathroom = new Room(lights2, doors2, "bathroom");

        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom));

        Set<Object> unvisitedObjects = new HashSet<>();
        unvisitedObjects.add(smartHome);
        unvisitedObjects.add(kitchen);
        unvisitedObjects.add(bathroom);
        unvisitedObjects.addAll(lights1);
        unvisitedObjects.addAll(lights2);
        unvisitedObjects.addAll(doors1);
        unvisitedObjects.addAll(doors2);
        smartHome.processAction(unvisitedObjects::remove);
        System.out.println(unvisitedObjects.isEmpty());
        assertTrue(unvisitedObjects.isEmpty());
    }

}
