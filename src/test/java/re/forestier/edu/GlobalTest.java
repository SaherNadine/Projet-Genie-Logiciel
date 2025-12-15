package re.forestier.edu;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.Player;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
        player.addXp(20);  
        player.inventory = new ArrayList<>();

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testToString() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
        
        Item sword = new Item("Épée Longue", "Une épée bien aiguisée", 5, 150);
        Item potion = new Item("Potion de Vie", "Restaure 50 HP", 1, 50);
        player.addItem(sword);
        player.addItem(potion);

        verify(player.toString());
    }

    @Test
    void testAffichageMarkdown() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
        
        Item sword = new Item("Épée Longue", "Une épée bien aiguisée", 5, 150);
        Item potion = new Item("Potion de Vie", "Restaure 50 HP", 1, 50);
        player.addItem(sword);
        player.addItem(potion);

        verify(player.toMarkdown());
    }
}