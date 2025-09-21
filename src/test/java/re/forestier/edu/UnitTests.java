package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Class Avatar ")
    void testAvatar() {
        player p1 = new player("Nadine", "Grognak", "NO", 10, new ArrayList<>());
        assertThat(p1.playerName, is(nullValue()));

        player p2 = new player("Aya", "Grognak", "ARCHER", 10, new ArrayList<>());
        assertThat(p2.playerName, is("Aya"));

        player p3 = new player("Me", "Grognak", "DWARF", 10, new ArrayList<>());
        assertThat(p3.playerName, is("Me"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            p.removeMoney(200);
            
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Positive money")
    void testPositiveMoney() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        p1.removeMoney(100);
        assertThat(p1.money, is(0));

        p1.addMoney(100);
        assertThat(p1.money, is(100));
    }

    @Test
    @DisplayName("Test level")
    void testLevel() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p1.retrieveLevel(), is(1));
        assertThat(p1.getXp(), is(0));

        UpdatePlayer up= new UpdatePlayer();

        up.addXp(p1,11);
        assertThat(p1.retrieveLevel(), is(2));

        up.addXp(p1,28);
        assertThat(p1.retrieveLevel(), is(3));

        up.addXp(p1,58);
        assertThat(p1.retrieveLevel(), is(4));

        up.addXp(p1,113);
        assertThat(p1.retrieveLevel(), is(5));

        up.addXp(p1, 0);
        assertThat(p1.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Affichage : constructeur par défaut")
    void testConstructeurAffichage() {
          new Affichage();
}

    @Test
    @DisplayName("tester les points de vie")
    void testPV() {
      player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      p.abilities.put("Force", 5);
      p.inventory.add("Épée");

      String texte = Affichage.afficherJoueur(p);
    }  

    @Test
    @DisplayName("tester la mise à jour des points de vie")
    void testMsj() {
      player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      UpdatePlayer up= new UpdatePlayer();
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(p.healthpoints));
      p.currenthealthpoints = 1;
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(p.healthpoints));
      assertThat(p.healthpoints, is(0));

    }  

}
