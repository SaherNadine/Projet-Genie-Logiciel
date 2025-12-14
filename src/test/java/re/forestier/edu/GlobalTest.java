package re.forestier.edu;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
        player.addXp(20);
        player.inventory = new ArrayList<>();

        verify(Affichage.afficherJoueur(player));
    }
}
