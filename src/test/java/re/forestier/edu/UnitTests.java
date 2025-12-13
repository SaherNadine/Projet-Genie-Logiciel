package re.forestier.edu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Goblin;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        Player player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());

        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Class Avatar ")
    void testAvatar() {

        Player p2 = new Archer("Aya", "Grognak", 10, new ArrayList<>());
        assertThat(p2.playerName, is("Aya"));

        Player p3 = new Dwarf("Me", "Grognak", 10, new ArrayList<>());
        assertThat(p3.playerName, is("Me"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(200));
    }

    @Test
    @DisplayName("Positive money")
    void testPositiveMoney() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());
        p1.removeMoney(100);
        assertThat(p1.money, is(0));

        p1.addMoney(100);
        assertThat(p1.money, is(100));
    }

    @Test
    @DisplayName("Test boundary level 1 to 2")
    void testBoundaryLevel1To2() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());
        assertThat(p1.retrieveLevel(), is(1));
        assertThat(p1.getXp(), is(0));

        p1.addXp(9);
        assertThat(p1.retrieveLevel(), is(1));

        p1.addXp(1);
        assertThat(p1.retrieveLevel(), is(2));
      }

    @Test
    @DisplayName("Test initial level is 1 not 0")
    void testInitialLevelIsOne() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());
        assertThat(p1.retrieveLevel(), is(1));
        assertNotEquals(0, p1.retrieveLevel());
    }

    @Test
    @DisplayName("Test boundary level 2 to 3")
    void testBoundaryLevel2To3() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());

        p1.addXp(26);
        assertThat(p1.retrieveLevel(), is(2));

        p1.addXp(1);
        assertThat(p1.retrieveLevel(), is(3));
        assertThat(p1.getXp(), is(27));
    }

    @Test
    @DisplayName("Test boundary level 3 to 4")
    void testBoundaryLevel3To4() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());

        p1.addXp(56);
        assertThat(p1.retrieveLevel(), is(3));

        p1.addXp(1);
        assertThat(p1.retrieveLevel(), is(4));
    }

    @Test
    @DisplayName("Test boundary level 4 to 5")
    void testBoundaryLevel4To5() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());

        p1.addXp(110);
        assertThat(p1.retrieveLevel(), is(4));

        p1.addXp(1);
        assertThat(p1.retrieveLevel(), is(5));

        p1.addXp(0);
        assertThat(p1.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Test level 5 is not 0")
    void testLevel5IsNotZero() {
        Player p1 = new Adventurer("Nadine", "Grognak", 100, new ArrayList<>());

        p1.addXp(113);
        assertThat(p1.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Test addXp retourne true quand level up")
    void testAddXpReturnsTrueOnLevelUp() {
       Player p = new Adventurer("Nadine", "Grognak", 0, new ArrayList<>());
       boolean result = p.addXp(10);
       assertThat(result, is(true));
      }

    @Test
    @DisplayName("Test addXp retourne false sans level up")
    void testAddXpReturnsFalseNoLevelU() {
       Player p = new Adventurer("Nadine", "Grognak", 0, new ArrayList<>());
       boolean result = p.addXp(5); 
       assertThat(result, is(false));
      }

    @Test
    @DisplayName("Affichage : constructeur par défaut")
    void testConstructeurAffichage() {
          new Affichage();
}

    @Test
    @DisplayName("tester les points de vie")
    void testPV() {
      Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
      p.abilities.put("Force", 5);
      p.inventory.add("Épée");

      String texte = Affichage.afficherJoueur(p);
    } 

    @Test
    @DisplayName("Test HP à 0 - joueur KO")
    void testPlayerKO() {
      Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
      UpdatePlayer up= new UpdatePlayer();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(0));
      String output = outputStream.toString();
      assertThat(output.trim(), is("Le joueur est KO !"));
    }

    @Test
    @DisplayName("Test HP >= max - pas de changement")
    void testHPAtMax() {
      Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
      UpdatePlayer up = new UpdatePlayer();

      p.healthpoints = 100;
      p.currenthealthpoints = 100;
      up.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(100));
    }

    @Test
    @DisplayName("Test HP = current")
    void testHPAtCurrent() {
      Player p = new Archer("Florian", "Grognak le barbare", 100, new ArrayList<>());
      p.healthpoints = 100;
      p.currenthealthpoints = 100;
      UpdatePlayer.majFinDeTour(p);
      assertThat(p.currenthealthpoints, is(p.healthpoints));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Holy Elixir avec Dwarf")
    void testDwarfHolyElixir() {
      Player p2 = new Dwarf("Nadine", "Grognak le barbare", 100, new ArrayList<>());
      p2.inventory.add("Holy Elixir");
      p2.currenthealthpoints = 1;
      p2.healthpoints = 4;
      UpdatePlayer up2= new UpdatePlayer();
      up2.majFinDeTour(p2);
      assertThat(p2.currenthealthpoints, is(3));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Holy Elixir avec Archer")
    void testArcherHolyElixir() {
      Player p3 = new Archer("Aya", "Grognak le barbare", 100, new ArrayList<>());
      p3.inventory.add("Holy Elixir");
      p3.currenthealthpoints = 1;
      p3.healthpoints = 4;
      UpdatePlayer up3 = new UpdatePlayer();
      up3.majFinDeTour(p3);
      assertThat(p3.currenthealthpoints, is(2));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie Magic Bow avec Archer")
    void testArcherMagicBow() {
      Player p4 = new Archer("Aya", "Grognak le barbare", 100, new ArrayList<>());
      p4.inventory.add("Magic Bow");
      p4.currenthealthpoints = 1;
      p4.healthpoints = 4;
      UpdatePlayer up4 = new UpdatePlayer();
      up4.majFinDeTour(p4);
      assertThat(p4.currenthealthpoints, is(1));
    }
    
    @Test
    @DisplayName("tester la mise à jour des points de vie DWARF ")
    void testDwarf() {
      Player p5 = new Dwarf("nadine", "Grognak le barbare", 100, new ArrayList<>());
      p5.currenthealthpoints = 1;
      p5.healthpoints = 4;
      UpdatePlayer up5= new UpdatePlayer();
      up5.majFinDeTour(p5);
      assertThat(p5.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie ADVENTURER bas niveau (< 3)")
    void testAdventurerLowLevel() {
      Player p6 = new Adventurer("Assia", "Grognak le barbare", 100, new ArrayList<>());
      p6.currenthealthpoints = 1;
      p6.healthpoints = 4;
      UpdatePlayer up6= new UpdatePlayer();
      up6.majFinDeTour(p6);
      assertThat(p6.currenthealthpoints, is(2)); 
    }

    @Test
    @DisplayName("tester la mise à jour des points de vie ADVENTURER haut niveau (>= 3)")
    void testAdventurerHighLevel() {
      Player p6 = new Adventurer("Assia", "Grognak le barbare", 100, new ArrayList<>());
      p6.currenthealthpoints = 1;
      p6.healthpoints = 4;
      UpdatePlayer up6 = new UpdatePlayer();
      p6.addXp(28);
      up6.majFinDeTour(p6);
      assertThat(p6.currenthealthpoints, is(3));
    }


    @Test
    @DisplayName("tester la mise à jour des points de vie à exactement 50% HP - pas de changement")
    void test() {
      Player p5 = new Dwarf("aya", "Grognak le barbare", 100, new ArrayList<>());
      p5.currenthealthpoints = 5;
      p5.healthpoints = 10;
      UpdatePlayer up5= new UpdatePlayer();
      up5.majFinDeTour(p5);
      assertThat(p5.currenthealthpoints, is(5));
    }


    @Test
    @DisplayName("Test Main")
    void testMain() {
        String[] args = {};
        Main.main(args);
    }


    @Test
    @DisplayName("Test classe invalide lance exception")
    void testInvalidClassThrowsException() {
        try {
            new Player("Nadine", "Hero", "BIZZARE", 100, new ArrayList<>()) {
               @Override
               protected void initializeAbilities() {}
            
               @Override
               protected HashMap<String, Integer> getAbilitiesForLevel(int level) {
                  return new HashMap<>();
              }
            };
            fail("Une exception devrait être lancée");
        } catch (IllegalArgumentException e) {
           assertThat(e.getMessage(), is("Unknown avatar class: BIZZARE"));
          }
    }

    @Test
    @DisplayName("Test montée niveau 4 Dwarf")
    void testDwarfLevel4Abilities() {
        Player p = new Dwarf("Nadine", "Smith", 50, new ArrayList<>());
        p.addXp(57);
        assertThat(p.abilities.get("DEF"), is(2));
    }

    @Test
    @DisplayName("Test montée niveau 5 Dwarf")
    void testDwarfLevel5Abilities() {
        Player p = new Dwarf("Nadine", "Smith", 50, new ArrayList<>());
        p.addXp(111);
        assertThat(p.abilities.get("CHA"), is(1));
    }


    @Test
    void testAdventurerLevel2AbilitiesUpdate() {
       Player p = new Adventurer("Nadine", "Hero", 0, new ArrayList<>());
    
       assertThat(p.abilities.get("INT"), is(1));
       assertThat(p.abilities.get("CHA"), is(2));
    
       p.addXp(10);
    
       assertThat(p.abilities.get("INT"), is(2));
       assertThat(p.abilities.get("CHA"), is(3));
}
    @Test
    @DisplayName("Test création Gobelin")
    void testGoblinCreation() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        assertThat(p.playerName, is("Nadine"));
        assertThat(p.getAvatarClass(), is("GOBLIN"));
}

    @Test
    @DisplayName("Test capacités initiales Gobelin niveau 1")
    void testGoblinLevel1Abilities() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ATK"), is(2));
        assertThat(p.abilities.get("ALC"), is(1));
}

     @Test
     @DisplayName("Test montée niveau 2 Gobelin")
     void testGoblinLevel2Abilities() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        p.addXp(10);
    
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("ALC"), is(4));
}

     @Test
     @DisplayName("Test montée niveau 3 Gobelin")
     void testGoblinLevel3Abilities() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        p.addXp(27); 
    
        assertThat(p.abilities.get("VIS"), is(1));
}


     @Test
     @DisplayName("Test montée niveau 4 Gobelin")
     void testGoblinLevel4Abilities() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        p.addXp(57); 
    
        assertThat(p.abilities.get("DEF"), is(1));
}

    @Test
    @DisplayName("Test montée niveau 5 Gobelin")
    void testGoblinLevel5Abilities() {
        Player p = new Goblin("Nadine", "Sneaky Goblin", 50, new ArrayList<>());
    
        p.addXp(111); 
    
        assertThat(p.abilities.get("DEF"), is(2));
        assertThat(p.abilities.get("ATK"), is(4));
}




}
