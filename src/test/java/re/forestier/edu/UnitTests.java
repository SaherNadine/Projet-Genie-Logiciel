package re.forestier.edu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Goblin;
import re.forestier.edu.rpg.Item;
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
    @DisplayName("Test removeMoney avec montant négatif")
    void testRemoveNegativeAmount() {
        Player p = new Adventurer("Nadine", "Hero", 100, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(-10));
    }

    @Test
    @DisplayName("Test removeMoney avec montant 0")
    void testRemoveZeroAmount() {
       Player p = new Adventurer("Nadine", "Hero", 100, new ArrayList<>());
       p.removeMoney(0);
       assertThat(p.money, is(100));
     }

    @Test
    @DisplayName("Test addMoney avec montant 0")
    void testAddZeroAmount() {
       Player p = new Adventurer("Nadine", "Hero", 100, new ArrayList<>());
       p.addMoney(0);
       assertThat(p.money, is(100));
     }

    @Test
    @DisplayName("Test addMoney avec montant négatif")
    void testAddNegativeAmount() {
       Player p = new Adventurer("Nadine", "Hero", 100, new ArrayList<>());
       assertThrows(IllegalArgumentException.class, () -> p.addMoney(-10));
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
    @DisplayName("Test plafonnerVie quand HP dépasse le max")
    void testPlafonnerVieDepasse() {
        Player p = new Adventurer("Nadine", "Hero", 100, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 105;  
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(100));
}

    @Test
    @DisplayName("tester les points de vie")
    void testPV() {
      Player p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
      p.abilities.put("Force", 5);
      Item epee = new Item("Épée", "Une épée", 5, 100);
      p.inventory.add(epee);
      assertThat(epee.getName(), is("Épée"));
      assertThat(epee.getDescription(), is("Une épée"));
      assertThat(epee.getWeight(), is(5));
      assertThat(epee.getValue(), is(100));
      assertTrue(epee.toString().contains("Épée"));

      String texte = Affichage.afficherJoueur(p);
      assertTrue(texte.contains("Épée"));

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
      p2.inventory.add(new Item("Holy Elixir", "Recover your HP", 1, 100));
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
      p3.inventory.add(new Item("Holy Elixir", "Recover your HP", 1, 100));
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
      p4.inventory.add(new Item("Magic Bow", "A magical bow", 3, 150));
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
        } catch (IllegalArgumentException e) {
           assertThat(e.getMessage(), is("Unknown avatar class: BIZZARE"));
          }
    }

    @Test
    @DisplayName("Test montée niveau 2 Dwarf")
    void testDwarfLevel2Abilities() {
        Player p = new Dwarf("Nadine", "Smith", 50, new ArrayList<>());
        p.addXp(10);
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
    }

    @Test
    @DisplayName("Test montée niveau 3 Dwarf")
    void testDwarfLevel3Abilities() {
        Player p = new Dwarf("Nadine", "Smith", 50, new ArrayList<>());
        p.addXp(27);
        assertThat(p.abilities.get("ATK"), is(4));
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
    @DisplayName("Test montée niveau 2 Archer")
    void testArcherLevel2Abilities() {
       Player p = new Archer("Nadine", "Hunter", 50, new ArrayList<>());
       p.addXp(10); 
       assertThat(p.abilities.get("DEF"), is(1));
       assertThat(p.abilities.get("CHA"), is(2));
    }

    @Test
    @DisplayName("Test montée niveau 3 Archer")
    void testArcherLevel3Abilities() {
       Player p = new Archer("Nadine", "Hunter", 50, new ArrayList<>());
       p.addXp(27); 
       assertThat(p.retrieveLevel(), is(3));
       assertThat(p.abilities.get("ATK"), is(3));
    }

    
    @Test
    @DisplayName("Test montée niveau 4 Archer")
    void testArcherLevel4Abilities() {
       Player p = new Archer("Nadine", "Hunter", 50, new ArrayList<>());
       p.addXp(57); 
       assertThat(p.abilities.get("DEF"), is(2));
    }

    @Test
    @DisplayName("Test montée niveau 5 Archer")
    void testArcherLevel5Abilities() {
       Player p = new Archer("Nadine", "Hunter", 50, new ArrayList<>());
       p.addXp(111); 
       assertThat(p.abilities.get("ATK"), is(4));
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


@Test
@DisplayName("addItem retourne true quand ajout réussit")
void testAddItemReturnsTrue() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    Item item = new Item("Épée", "Une épée", 5, 100);
    
    boolean result = p.addItem(item);
    
    assertThat(result, is(true)); 
    assertThat(p.getInventorySize(), is(1));
}

@Test
@DisplayName("addItem retourne false quand poids dépassé")
void testAddItemReturnsFalse() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    Item heavyItem = new Item("Armure", "Lourde", 150, 500);
    
    boolean result = p.addItem(heavyItem);
    
    assertThat(result, is(false)); 
    assertThat(p.getInventorySize(), is(0));
}

@Test
@DisplayName("addItem à la limite exacte du poids")
void testAddItemExactLimit() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    Item item1 = new Item("Item1", "Desc", 50, 100);
    Item item2 = new Item("Item2", "Desc", 50, 100);
    Item item3 = new Item("Item3", "Desc", 1, 10);
    
    assertTrue(p.addItem(item1)); 
    assertTrue(p.addItem(item2)); 
    assertFalse(p.addItem(item3)); 
}

@Test
@DisplayName("sellItem ajoute l'argent correctement")
void testSellItemAddsMoney() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    Item item = new Item("Épée", "Une épée", 5, 100);
    p.addItem(item);
    
    int moneyBefore = p.money;
    p.sellItem(0);
    
    assertThat(p.money, is(moneyBefore + 100));
    assertThat(p.getInventorySize(), is(0));
}

@Test
@DisplayName("sellItem lance exception pour index négatif")
void testSellItemNegativeIndex() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    assertThrows(IndexOutOfBoundsException.class, () -> {
        p.sellItem(-1); 
    });
}

@Test
@DisplayName("sellItem lance exception pour index trop grand")
void testSellItemIndexTooLarge() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Item", "Desc", 1, 10));
    
    assertThrows(IndexOutOfBoundsException.class, () -> {
        p.sellItem(1); 
    });
    
    assertThrows(IndexOutOfBoundsException.class, () -> {
        p.sellItem(5); 
    });
}

@Test
@DisplayName("sellItem à l'index 0 valide")
void testSellItemValidIndex() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Item1", "Desc", 1, 50));
    p.addItem(new Item("Item2", "Desc", 1, 75));
    
    p.sellItem(0); 
    assertThat(p.getInventorySize(), is(1));
}


@Test
@DisplayName("getCurrentWeight avec plusieurs items")
void testGetCurrentWeightMultiple() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Item1", "Desc", 10, 50));
    p.addItem(new Item("Item2", "Desc", 20, 100));
    p.addItem(new Item("Item3", "Desc", 15, 75));
    
    int weight = p.getCurrentWeight();
    
    assertThat(weight, is(45)); 
    assertThat(weight, not(0)); 
    assertTrue(weight > 0); 
}

@Test
@DisplayName("getCurrentWeight retourne 0 quand inventaire vide")
void testGetCurrentWeightEmpty() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    assertThat(p.getCurrentWeight(), is(0)); 
}


@Test
@DisplayName("getMaxWeight retourne la valeur correcte")
void testGetMaxWeight() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    assertThat(p.getMaxWeight(), is(100)); 
    assertThat(p.getMaxWeight(), not(equalTo(0)));
}

@Test
@DisplayName("getInventorySize avec plusieurs items")
void testGetInventorySizeMultiple() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Item1", "Desc", 5, 50));
    p.addItem(new Item("Item2", "Desc", 5, 50));
    p.addItem(new Item("Item3", "Desc", 5, 50));
    
    assertThat(p.getInventorySize(), is(3)); 
    assertThat(p.getInventorySize(), not(0));
}

@Test
@DisplayName("getInventorySize retourne 0 pour inventaire vide")
void testGetInventorySizeEmpty() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    assertThat(p.getInventorySize(), is(0));
}

@Test
@DisplayName("toString retourne une chaîne non vide")
void testToStringNotEmpty() {
    Player p = new Adventurer("Alice", "Warrior", 100, new ArrayList<>());
    
    String result = p.toString();
    
    assertThat(result, not(equalTo(""))); 
    assertTrue(result.length() > 0);
}

@Test
@DisplayName("toString avec capacités affiche bien les capacités")
void testToStringWithAbilities() {
    Player p = new Adventurer("Bob", "Knight", 100, new ArrayList<>());
    p.abilities.put("Force", 5);
    p.abilities.put("Défense", 3);
    
    String result = p.toString();
    
    assertTrue(result.contains("Force"));
    assertTrue(result.contains("Défense"));
}

@Test
@DisplayName("toString avec inventaire vide affiche (vide)")
void testToStringEmptyInventory() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    String result = p.toString();
    
    assertTrue(result.contains("vide") || result.contains("Inventaire"));
}

@Test
@DisplayName("toString avec inventaire plein affiche les items")
void testToStringWithInventory() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Épée", "Une épée magique", 5, 100));
    
    String result = p.toString();
    
    assertTrue(result.contains("Épée"));
    assertTrue(result.contains("Une épée magique"));
    assertFalse(result.contains("vide"));
}

@Test
@DisplayName("toMarkdown retourne une chaîne non vide")
void testToMarkdownNotEmpty() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    
    String result = p.toMarkdown();
    
    assertThat(result, not(equalTo(""))); 
    assertTrue(result.length() > 0);
}

@Test
@DisplayName("toMarkdown contient le formatage Markdown")
void testToMarkdownFormat() {
    Player p = new Adventurer("Charlie", "Mage", 100, new ArrayList<>());
    
    String result = p.toMarkdown();
    
    assertTrue(result.contains("#")); 
    assertTrue(result.contains("**")); 
    assertTrue(result.contains("Charlie"));
}

@Test
@DisplayName("toMarkdown avec capacités")
void testToMarkdownWithAbilities() {
    Player p = new Archer("Diana", "Hunter", 100, new ArrayList<>());
    p.abilities.put("Agilité", 4);
    
    String result = p.toMarkdown();
    
    assertTrue(result.contains("Agilité"));
    assertTrue(result.contains("Capacités"));
}

@Test
@DisplayName("toMarkdown avec inventaire vide")
void testToMarkdownEmptyInventory() {
    Player p = new Dwarf("Test", "Avatar", 100, new ArrayList<>());
    
    String result = p.toMarkdown();
    
    assertTrue(result.contains("Vide") || result.contains("Inventaire"));
}

@Test
@DisplayName("toMarkdown avec inventaire plein")
void testToMarkdownWithInventory() {
    Player p = new Adventurer("Test", "Avatar", 100, new ArrayList<>());
    p.addItem(new Item("Bouclier", "Un bouclier solide", 10, 150));
    
    String result = p.toMarkdown();
    
    assertTrue(result.contains("Bouclier"));
    assertTrue(result.contains("Un bouclier solide"));
}


}
