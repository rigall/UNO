package UNO;
import java.util.ArrayList;

public class Jugador{

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

    public void addCarta(Carta carta) {
        cartes.add(carta);
    }
}