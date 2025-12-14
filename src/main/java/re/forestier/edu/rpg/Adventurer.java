package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Adventurer extends Player {
    
    public Adventurer(String playerName, String avatar_name, int money, ArrayList<Item> inventory) {
        super(playerName, avatar_name,"ADVENTURER", money, inventory);
    }

    @Override
    public String getAvatarClass() {
        return "ADVENTURER";
    }

    @Override
    protected void initializeAbilities() {
        abilities.put("INT", 1);
        abilities.put("DEF", 1);
        abilities.put("ATK", 3);
        abilities.put("CHA", 2);
    }

    @Override
    protected HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> levelAbilities = new HashMap<>();
        
        switch (level) {
            case 2:
                levelAbilities.put("INT", 2);
                levelAbilities.put("CHA", 3);
                break;
            case 3:
                levelAbilities.put("ATK", 5);
                levelAbilities.put("ALC", 1);
                break;
            case 4:
                levelAbilities.put("DEF", 3);
                break;
            case 5:
                levelAbilities.put("VIS", 1);
                levelAbilities.put("DEF", 4);
                break;
        }
        
        return levelAbilities;
    }
}