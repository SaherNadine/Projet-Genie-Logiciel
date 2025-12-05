package re.forestier.edu.rpg;


public class UpdatePlayer {


    private static void regenererSelonClasse(Player player) {
        String classe = player.getAvatarClass();
    
        if (classe.equals("ADVENTURER")) {
            player.currenthealthpoints += 2;
            if (player.retrieveLevel() < 3) {
                player.currenthealthpoints -= 1;
            }
        }
            
        if (classe.equals("DWARF")) {
            if(player.inventory.contains("Holy Elixir")) {
                player.currenthealthpoints += 1;
            }
            player.currenthealthpoints += 1;
        }
        if (classe.equals("ARCHER")) {    
            player.currenthealthpoints += 1;
            if(player.inventory.contains("Magic Bow")) {
                player.currenthealthpoints += player.currenthealthpoints / 8 - 1;
            }
        }
    }

    private static void regenererSiSousLaMoitie(Player player){
        if(player.currenthealthpoints < player.healthpoints / 2) {
           regenererSelonClasse(player);
            }
    }


    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if(player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
        if(player.currenthealthpoints >= player.healthpoints){
            return;  
        }
        regenererSiSousLaMoitie(player);
        
    }
}
