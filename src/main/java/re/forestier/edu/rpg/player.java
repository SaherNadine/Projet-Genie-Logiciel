package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    protected abstract void initializeAbilities();

    protected abstract HashMap<String, Integer> getAbilitiesForLevel(int level);

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities = new HashMap<>();
        initializeAbilities();
    }


    public boolean addXp(int xp) {
        int currentLevel = this.retrieveLevel();
        this.xp += xp;
        int newLevel = this.retrieveLevel();

        if (newLevel != currentLevel) {
            // Player leveled-up!
            // Give a random object
            Random random = new Random();
            this.inventory.add(objectList[random.nextInt(objectList.length)]);

            // Add/upgrade abilities to player
            HashMap<String, Integer> abilities = this.getAbilitiesForLevel(newLevel);
            abilities.forEach((ability, level) -> {
                this.abilities.put(ability, abilities.get(ability));
            });
            return true;
        }
        return false;
    }

    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to remove can't be negative!");
        }

        money -= amount;
    }
    public void addMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add can't be negative!");
        }
        money += amount;
    }
    public int retrieveLevel() {
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10); // 1*10 + ((2*0)/4)
        levels.put(3,27); // 2*10 + ((3*10)/4)
        levels.put(4,57); // 3*10 + ((4*27)/4)
        levels.put(5,111); // 4*10 + ((5*57)/4)
        //TODO : ajouter les prochains niveaux

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }

     public void updateAbilitiesForLevel(int newLevel) {
        HashMap<String, Integer> newAbilities = getAbilitiesForLevel(newLevel);
        newAbilities.forEach((ability, value) -> abilities.put(ability, value));
    }

}