package uno.logica;
import uno.interficie.UI;

import java.util.ArrayList;

public class Jugador {
    private String nom;
    private ArrayList<Carta> cartes;

    public Jugador(String nom) {
        this.nom = nom;
        cartes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Carta> getCartes() {
        return cartes;
    }

    public void tirarCarta(Carta carta, Pilo pilo) {
        if (cartes.contains(carta)) {
            cartes.remove(carta);
            pilo.addCarta(carta);
            System.out.println(nom + " ha tirat la carta:");
            UI.mostrarCarta(carta);
        } else {
            System.out.println("No tens aquesta carta");
        }
    }

    public void robarCarta(Mazo mazo) {
        if (!mazo.esBuit()) {
            Carta cartarobada = mazo.agafarCarta();
            cartes.add(cartarobada);
            System.out.println(nom + " ha robat una carta");
        }
    }

    public int nombreDeCartes() {
        return cartes.size();
    }

    public boolean potTirarCarta(Carta cartaSuperior) {
        for (Carta cartaJugador : cartes) {
            if (Regles.sonCartesCompatibles(cartaJugador, cartaSuperior)) {
                return true;
            }
        }
        return false;
    }
}