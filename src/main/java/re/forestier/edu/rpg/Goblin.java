package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Goblin extends Player {
    
    public Goblin(String playerName, String avatar_name, int money, ArrayList<Item> inventory) {
        super(playerName, avatar_name,"GOBLIN", money, inventory);
    }

    @Override
    public String getAvatarClass() {
        return "GOBLIN";
    }

    @Override
    protected void initializeAbilities() {
        abilities.put("INT", 2);
        abilities.put("ATK", 2);
        abilities.put("ALC", 1);
    }

    @Override
    protected HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> levelAbilities = new HashMap<>();
        
        switch (level) {
            case 2:
                levelAbilities.put("ATK", 3);
                levelAbilities.put("ALC", 4);
                break;
            case 3:
                levelAbilities.put("VIS", 1);
                break;
            case 4:
                levelAbilities.put("DEF", 1);
                break;
            case 5:
                levelAbilities.put("DEF", 2);
                levelAbilities.put("ATK", 4);
                break;
        }
        
        return levelAbilities;
    }
}