package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Dwarf extends Player {
    
    public Dwarf(String playerName, String avatar_name, int money, ArrayList<String> inventory) {
        super(playerName, avatar_name,"DWARF", money, inventory);
    }

    @Override
    public String getAvatarClass() {
        return "DWARF";
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
        
        switch (level) {
            case 1:
                levelAbilities.put("ALC", 4);
                levelAbilities.put("INT", 1);
                levelAbilities.put("ATK", 3);
                break;
            case 2:
                levelAbilities.put("DEF", 1);
                levelAbilities.put("ALC", 5);
                break;
            case 3:
                levelAbilities.put("ATK", 4);
                break;
            case 4:
                levelAbilities.put("DEF", 2);
                break;
            case 5:
                levelAbilities.put("CHA", 1);
                break;
        }
        
        return levelAbilities;
    }
}
