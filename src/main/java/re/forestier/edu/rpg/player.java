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
    
    private int maxWeight = 100; // Poids maximal que le joueur peut porter

    protected abstract void initializeAbilities();

    protected abstract HashMap<String, Integer> getAbilitiesForLevel(int level);

    // Nouvelle liste d'objets sous forme d'Items
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
    public ArrayList<Item> inventory; // Changé de String à Item

    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<Item> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") && !avatarClass.equals("GOBLIN") ) {
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
            Item randomItem = objectList[random.nextInt(objectList.length)];
            addItem(randomItem); // Utilise addItem au lieu d'ajouter directement

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

    // ===== NOUVELLES MÉTHODES POUR LA GESTION DES ITEMS =====
    
    /**
     * Ajoute un item à l'inventaire si le poids maximal n'est pas dépassé
     * @param item L'item à ajouter
     * @return true si l'ajout a réussi, false si le poids maximal est dépassé
     */
    public boolean addItem(Item item) {
        if (getCurrentWeight() + item.getWeight() > maxWeight) {
            return false; // Poids maximal dépassé
        }
        inventory.add(item);
        return true;
    }

    /**
     * Vend un item de l'inventaire et ajoute sa valeur à l'argent du joueur
     * @param index L'index de l'item à vendre
     * @throws IndexOutOfBoundsException si l'index est invalide
     */
    public void sellItem(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= inventory.size()) {
            throw new IndexOutOfBoundsException("Invalid inventory index");
        }
        Item item = inventory.remove(index);
        addMoney(item.getValue());
    }

    /**
     * Calcule le poids total actuel de l'inventaire
     * @return Le poids total
     */
    public int getCurrentWeight() {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Retourne le poids maximal que le joueur peut porter
     * @return Le poids maximal
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Retourne le nombre d'items dans l'inventaire
     * @return La taille de l'inventaire
     */
    public int getInventorySize() {
        return inventory.size();
    }
}