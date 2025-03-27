package UNO;

public class Main{
    public static void main(String[] args) {
        Carta c1 = new Carta(Carta.colors.Groc, 1);
        Carta c2 = new Carta(Carta.colors.Vermell, 3);
        Carta c3 = new Carta(Carta.colors.Blau, 6);
        Carta c4 = new Carta(Carta.colors.Verd, 9);

        Jugador j1 = new Jugador("Jugador 1");
        j1.addCarta(c1);
        j1.addCarta(c2);
        j1.addCarta(c3);
        j1.addCarta(c4);

        for (Carta c : j1.getCartes()) {
            UI.mostrarCarta(c);
        }

        UI.mostrarCartes(j1.getCartes());
    }
}