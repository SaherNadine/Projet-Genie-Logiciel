package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testItemCreation() {
        Item item = new Item("Épée", "Une épée tranchante", 5, 100);
        
        assertEquals("Épée", item.getName());
        assertEquals("Une épée tranchante", item.getDescription());
        assertEquals(5, item.getWeight());
        assertEquals(100, item.getValue());
    }

    @Test
    public void testItemWithZeroWeight() {
        Item item = new Item("Plume", "Une plume légère", 0, 10);
        assertEquals(0, item.getWeight());
    }

    @Test
    public void testItemToString() {
        Item item = new Item("Potion", "Restaure 50 HP", 2, 50);
        String expected = "Potion (2kg, 50 gold) - Restaure 50 HP";
        assertEquals(expected, item.toString());
    }
}