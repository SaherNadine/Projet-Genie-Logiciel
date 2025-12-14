package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerInventoryTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Goblin("TestPlayer", "TestAvatar", 100, new ArrayList<>());
    }

    @Test
    public void testAddItemWithinWeightLimit() {
        Item item = new Item("Épée", "Une épée", 5, 100);
        assertTrue(player.addItem(item));
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void testAddItemExceedsWeightLimit() {
        Item heavyItem = new Item("Armure lourde", "Très lourde", 50, 500);
        player.addItem(heavyItem);
        
        Item anotherHeavyItem = new Item("Bouclier géant", "Énorme", 50, 300);
        assertFalse(player.addItem(anotherHeavyItem));
    }

    @Test
    public void testGetCurrentWeight() {
        Item item1 = new Item("Épée", "Une épée", 5, 100);
        Item item2 = new Item("Bouclier", "Un bouclier", 8, 150);
        
        player.addItem(item1);
        player.addItem(item2);
        
        assertEquals(13, player.getCurrentWeight());
    }

    @Test
    public void testSellItem() {
        Item item = new Item("Potion", "Une potion", 2, 50);
        player.addItem(item);
        
        int initialMoney = player.money;
        player.sellItem(0);
        
        assertEquals(initialMoney + 50, player.money);
        assertEquals(0, player.getInventorySize());
    }

    @Test
    public void testEmptyInventoryWeight() {
        assertEquals(0, player.getCurrentWeight());
    }
}