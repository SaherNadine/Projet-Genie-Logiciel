package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Player {
    public String playerName;
    public String avatarName;
    private String avatarClass;

     private int maxWeight = 100;

    public Integer money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    protected abstract void initializeAbilities();

    protected abstract HashMap<String, Integer> getAbilitiesForLevel(int level);

    private final static Item[] objectList = {
        new Item("Lookout Ring", "Prevents surprise attacks", 1, 50),
        new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 30),
        new Item("Draupnir", "Increases XP gained by 100%", 2, 200),
        new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 80),
        new Item("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?", 5, 150),
        new Item("Combat Edge", "Well, that's an edge", 3, 60),
        new Item("Holy Elixir", "Recover your HP", 1, 100)
    };

    public HashMap<String, Integer> abilities;
    public ArrayList<Item> inventory;


    private final static HashMap<Integer, Integer> LEVEL_THRESHOLDS = new HashMap<>() {{
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        put(2, 10);   // 1*10 + ((2*0)/4)
        put(3, 27);   // 2*10 + ((3*10)/4)
        put(4, 57);   // 3*10 + ((4*27)/4)
        put(5, 111);  // 4*10 + ((5*57)/4)
    }};

    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<Item> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") && !avatarClass.equals("GOBLIN") ) {
            return;
        }

        this.playerName = playerName;
        this.avatarName = avatar_name;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities = new HashMap<>();
        initializeAbilities();
    }


    public void updateAbilitiesForLevel(int newLevel) {
        HashMap<String, Integer> newAbilities = getAbilitiesForLevel(newLevel);
        newAbilities.forEach((ability, value) -> abilities.put(ability, value));
    }


    public boolean addXp(int xp) {
        int currentLevel = this.retrieveLevel();
        this.xp += xp;
        int newLevel = this.retrieveLevel();

        if (newLevel != currentLevel) {
            // Player leveled-up!
            // Give a random object
            Random random = new Random();
            Item randomItem = objectList[random.nextInt(objectList.length)];
            addItem(randomItem);

            // Add/upgrade abilities to player
            updateAbilitiesForLevel(newLevel);
            return true;
        }
        return false;
    }

    public String getAvatarClass () {
        return avatarClass;
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
        //TODO : ajouter les prochains niveaux
        if (xp < LEVEL_THRESHOLDS.get(2)) return 1;
        if (xp < LEVEL_THRESHOLDS.get(3)) return 2;
        if (xp < LEVEL_THRESHOLDS.get(4)) return 3;
        if (xp < LEVEL_THRESHOLDS.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }


    public boolean addItem(Item item) {
        if (getCurrentWeight() + item.getWeight() > maxWeight) {
            return false; // Poids maximal dépassé
        }
        inventory.add(item);
        return true;
    }

    public void sellItem(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= inventory.size()) {
            throw new IndexOutOfBoundsException("Invalid inventory index");
        }
        Item item = inventory.remove(index);
        addMoney(item.getValue());
    }

    public int getCurrentWeight() {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
    
    public int getInventorySize() {
        return inventory.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Joueur: ").append(playerName).append("\n");
        sb.append("Avatar: ").append(avatarName).append("\n");
        sb.append("Classe: ").append(getAvatarClass()).append("\n");
        sb.append("Niveau: ").append(retrieveLevel()).append("\n");
        sb.append("XP: ").append(xp).append("\n");
        sb.append("Argent: ").append(money).append(" pièces d'or\n");
        sb.append("Poids actuel: ").append(getCurrentWeight())
          .append("/").append(maxWeight).append(" kg\n\n");
        
        if (!abilities.isEmpty()) {
            sb.append("Capacités:\n");
            abilities.forEach((ability, abilityLevel) -> {
                sb.append("- ").append(ability).append(": Niveau ")
                  .append(abilityLevel).append("\n");
            });
            sb.append("\n");
        }
        
        sb.append("Inventaire:\n");
        if (inventory.isEmpty()) {
            sb.append("(vide)\n");
        } else {
            for (Item item : inventory) {
                sb.append("- ").append(item.getName()).append(" - ")
                  .append(item.getDescription()).append(" (Poids: ")
                  .append(item.getWeight()).append("kg, Valeur: ")
                  .append(item.getValue()).append(" pièces)\n");
            }
        }
        
        return sb.toString();
    }

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("# Joueur: ").append(playerName).append("\n\n");

        sb.append("## Informations\n\n");
        sb.append("* **Avatar:** ").append(avatarName).append("\n");
        sb.append("* **Classe:** ").append(getAvatarClass()).append("\n");
        sb.append("* **Niveau:** ").append(retrieveLevel()).append("\n");
        sb.append("* **XP:** ").append(xp).append("\n");
        sb.append("* **Argent:** ").append(money).append(" pièces d'or\n");
        sb.append("* **Poids actuel:** ").append(getCurrentWeight())
          .append("/").append(maxWeight).append(" kg\n\n");

        if (!abilities.isEmpty()) {
            sb.append("## Capacités\n\n");
            abilities.forEach((ability, abilityLevel) -> {
                sb.append("* **").append(ability).append(":** Niveau ")
                  .append(abilityLevel).append("\n");
            });
            sb.append("\n");
        }

        sb.append("## Inventaire\n\n");
        if (inventory.isEmpty()) {
            sb.append("*Vide*\n");
        } else {
            for (Item item : inventory) {
                sb.append("* **").append(item.getName()).append("** - *")
                  .append(item.getDescription()).append("* (Poids: ")
                  .append(item.getWeight()).append("kg, Valeur: ")
                  .append(item.getValue()).append(" pièces)\n");
            }
        }
        
        return sb.toString();
    }
}

