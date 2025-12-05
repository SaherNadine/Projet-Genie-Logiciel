package re.forestier.edu;
import java.util.ArrayList;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Player;

public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Dwarf("Florian", "Ruzberg de Rivehaute",  200, new ArrayList<>());
        firstPlayer.addMoney(400);

        firstPlayer.addXp(15);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
    }
}