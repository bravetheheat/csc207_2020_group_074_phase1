import main.gateways.CSVGateway;
import main.gateways.Gateway;
import org.junit.*;
import main.entities.*;

import java.util.ArrayList;
import java.util.List;

public class GatewayTest {

    Gateway gateway;

    @Before
    public void setUp(){
        this.gateway = new CSVGateway();
    }

    @Test
    public void testSaveRooms() {
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);

        List<Room> rooms= new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        this.gateway.saveRooms(rooms);



    }
}
