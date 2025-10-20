package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    @DisplayName("Test boundary level 1 to 2")
    void testBoundaryLevel1To2() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p1.retrieveLevel(), is(1));
        assertThat(p1.getXp(), is(0));

        UpdatePlayer up= new UpdatePlayer();
        up.addXp(p1,9);
        assertThat(p1.retrieveLevel(), is(1));

        up.addXp(p1,1);
        assertThat(p1.retrieveLevel(), is(2));
      }

    @Test
    @DisplayName("Test initial level is 1 not 0")
    void testInitialLevelIsOne() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p1.retrieveLevel(), is(1));
        assertNotEquals(0, p1.retrieveLevel());
    }

    @Test
    @DisplayName("Test boundary level 2 to 3")
    void testBoundaryLevel2To3() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer up= new UpdatePlayer();

        up.addXp(p1,26);
        assertThat(p1.retrieveLevel(), is(2));

        up.addXp(p1,1);
        assertThat(p1.retrieveLevel(), is(3));
        assertThat(p1.getXp(), is(27));
    }

    @Test
    @DisplayName("Test boundary level 3 to 4")
    void testBoundaryLevel3To4() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer up= new UpdatePlayer();

        up.addXp(p1,56);
        assertThat(p1.retrieveLevel(), is(3));

        up.addXp(p1,1);
        assertThat(p1.retrieveLevel(), is(4));
    }

    @Test
    @DisplayName("Test boundary level 4 to 5")
    void testBoundaryLevel4To5() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer up= new UpdatePlayer();

        up.addXp(p1,110);
        assertThat(p1.retrieveLevel(), is(4));

        up.addXp(p1,1);
        assertThat(p1.retrieveLevel(), is(5));

        up.addXp(p1,0);
        assertThat(p1.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Test level 5 is not 0")
    void testLevel5IsNotZero() {
        player p1 = new player("Nadine", "Grognak", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer up= new UpdatePlayer();

        up.addXp(p1,113);
        assertThat(p1.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Test addXp retourne true quand level up")
    void testAddXpReturnsTrueOnLevelUp() {
       player p = new player("Test", "Test", "ADVENTURER", 0, new ArrayList<>());
       boolean result = UpdatePlayer.addXp(p, 10);
       assertThat(result, is(true));
      }

    @Test
    @DisplayName("Test addXp retourne false sans level up")
    void testAddXpReturnsFalseNoLevelU() {
       player p = new player("Test", "Test", "ADVENTURER", 0, new ArrayList<>());
       boolean result = UpdatePlayer.addXp(p, 5); 
       assertThat(result, is(false));
      }

//     @Test
//     @DisplayName("Test que l'objet est bien ajouté au level up")
//     void testObjectAddedOnLevelUp() {
//        player p = new player("Test", "Test", "ADVENTURER", 0, new ArrayList<>());
//        int inventorySizeBefore = p.inventory.size();
//        UpdatePlayer.addXp(p, 10); 
//        int inventorySizeAfter = p.inventory.size();
//        assertThat(inventorySizeAfter, is(inventorySizeBefore + 1));
// }



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
    @DisplayName("Test HP à 0 - joueur KO")
    void testPlayerKO() {
      player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      UpdatePlayer up= new UpdatePlayer();
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(0));
    }

    @Test
    @DisplayName("Test HP >= max - pas de changement")
    void testHPAtMax() {
      player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      UpdatePlayer up= new UpdatePlayer();

      p.healthpoints = 100;
      p.currenthealthpoints = 100;
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(100));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Holy Elixir avec Dwarf")
    void testDwarfHolyElixir() {
      player p2 = new player("Nadine", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
      p2.inventory.add("Holy Elixir");
      p2.currenthealthpoints = 1;
      p2.healthpoints = 4;
      UpdatePlayer up2= new UpdatePlayer();
      up2.majFinDeTour(p2);
      assertThat(p2.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Holy Elixir avec Archer")
    void testArcherHolyElixir() {
      player p3 = new player("Aya", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
      p3.inventory.add("Holy Elixir");
      p3.currenthealthpoints = 1;
      p3.healthpoints = 4;
      UpdatePlayer up3= new UpdatePlayer();
      up3.majFinDeTour(p3);
      assertThat(p3.getAvatarClass(), is("ARCHER"));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Magic Bow avec Archer")
    void testArcherMagicBow() {
      player p4 = new player("Ikram", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
      p4.inventory.add("Magic Bow");
      p4.currenthealthpoints = 1;
      p4.healthpoints = 4;
      UpdatePlayer up4= new UpdatePlayer();
      up4.majFinDeTour(p4);
      assertThat(p4.getAvatarClass(), is("ARCHER"));
    }
    
    @Test
    @DisplayName("tester la mise à jour des points de vie DWARF ")
    void testDwarf() {
      player p5 = new player("walaa", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
      p5.currenthealthpoints = 1;
      p5.healthpoints = 4;
      UpdatePlayer up5= new UpdatePlayer();
      up5.majFinDeTour(p5);
      assertThat(p5.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie ADVENTURER ")
    void testAdventurer() {
      player p6 = new player("Assia", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      p6.currenthealthpoints = 1;
      p6.healthpoints = 4;
      UpdatePlayer up6= new UpdatePlayer();
      up6.majFinDeTour(p6);
      assertThat(p6.currenthealthpoints, is(2)); 
      p6.currenthealthpoints = 1;
      up6.addXp(p6,58);
      up6.majFinDeTour(p6);
      assertThat(p6.currenthealthpoints, is(3));
      assertThat(p6.retrieveLevel(), is(4));

        
      player p7 = new player("ATA", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
      p7.currenthealthpoints = 2;
      p7.healthpoints = 4;
      UpdatePlayer up7= new UpdatePlayer();
      up7.majFinDeTour(p7);
      assertThat(p7.getAvatarClass(), is("ADVENTURER")); 
    }


    @Test
    @DisplayName("Test Main")
    void testMain() {
        String[] args = {};
        Main.main(args);
    }
}
