package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Archer extends Player {
    
    public Archer(String playerName, String avatar_name, int money, ArrayList<String> inventory) {
        super(playerName, avatar_name,"ARCHER", money, inventory);
    }

    @Override
    public String getAvatarClass() {
        return "ARCHER";
    }

    @Override
    protected void initializeAbilities() {
        abilities.put("INT", 1);
        abilities.put("ATK", 3);
        abilities.put("CHA", 1);
        abilities.put("VIS", 3);
    }

    @Override
    protected HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> levelAbilities = new HashMap<>();
        
        switch (level) {
            case 2:
                levelAbilities.put("DEF", 1);
                levelAbilities.put("CHA", 2);
                break;
            case 3:
                levelAbilities.put("ATK", 3);
                break;
            case 4:
                levelAbilities.put("DEF", 2);
                break;
            case 5:
                levelAbilities.put("ATK", 4);
                break;
        }
        
        return levelAbilities;
    }
}
