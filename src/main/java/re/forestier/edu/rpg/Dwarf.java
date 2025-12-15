package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Dwarf extends Player {
    
    public Dwarf(String playerName, String avatar_name, int money, ArrayList<Item> inventory) {
        super(playerName, avatar_name,"DWARF", money, inventory);
    }

    @Override
    protected void initializeAbilities() {
        abilities.put("ALC", 4);
        abilities.put("INT", 1);
        abilities.put("ATK", 3);
    }

    @Override
    protected HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> levelAbilities = new HashMap<>();
        
        if (level == 2) {
            levelAbilities.put("DEF", 1);
            levelAbilities.put("ALC", 5);
        } 
        if (level == 3) {
            levelAbilities.put("ATK", 4);
        } 
        if (level == 4) {
            levelAbilities.put("DEF", 2);
        }
        if (level == 5) {
            levelAbilities.put("CHA", 1);
        }
        
        return levelAbilities;
    }
}
