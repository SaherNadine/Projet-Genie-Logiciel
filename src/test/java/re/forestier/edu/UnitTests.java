package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

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
      p.currenthealthpoints = 1;
      p.healthpoints = 4;
      assertThat(p.getAvatarClass(), is("ADVENTURER"));

      player p2 = new player("Nadine", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
      p2.inventory.add("Holy Elixir");
      p2.currenthealthpoints = 1;
      p2.healthpoints = 4;
      UpdatePlayer up2= new UpdatePlayer();
      up2.majFinDeTour(p2);
      assertThat(p2.getAvatarClass(), is("DWARF"));

      player p3 = new player("Aya", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
      p3.inventory.add("Holy Elixir");
      p3.currenthealthpoints = 1;
      p3.healthpoints = 4;
      UpdatePlayer up3= new UpdatePlayer();
      up3.majFinDeTour(p3);
      assertThat(p3.getAvatarClass(), is("ARCHER"));

      player p4 = new player("Ikram", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
      p4.inventory.add("Magic Bow");
      p4.currenthealthpoints = 1;
      p4.healthpoints = 4;
      UpdatePlayer up4= new UpdatePlayer();
      up4.majFinDeTour(p4);
      assertThat(p4.getAvatarClass(), is("ARCHER"));
    }  

    @Test
    @DisplayName("Test DWARF with Holy Elixir")
    void testDwarfWithHolyElixir() {
            player p5 = new player("walaa", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
            p5.currenthealthpoints = 1;
            p5.healthpoints = 4;
            UpdatePlayer up5= new UpdatePlayer();
            up5.majFinDeTour(p5);
            assertThat(p5.getAvatarClass(), is("DWARF"));
    
            player p6 = new player("Assia", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
            p6.currenthealthpoints = 1;
            p6.healthpoints = 4;
            UpdatePlayer up6= new UpdatePlayer();
            up6.majFinDeTour(p6);
            assertThat(p6.getAvatarClass(), is("ADVENTURER"));
        
        player p7 = new player("ATA", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p7.currenthealthpoints = 2;
        p7.healthpoints = 4;
        UpdatePlayer up7= new UpdatePlayer();
        up7.majFinDeTour(p7);
        assertThat(p7.getAvatarClass(), is("ADVENTURER")); }}
