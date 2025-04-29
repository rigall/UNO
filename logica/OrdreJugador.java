package uno.logica;

import java.util.ArrayList;
import java.util.Collections;

public class OrdreJugador {
    public ArrayList<Jugador> jugadors = new ArrayList<>();
    private int tornActual = 0; // Canviat a 0 per compatibilitat amb índexs de l'ArrayList

    public void passarTorn() {
        if (tornActual < jugadors.size() - 1) {
            tornActual++;
        } else {
            tornActual = 0;
        }
    }

    public Jugador getJugadorActiu() {
        return jugadors.get(tornActual);
    }

    public void crearJugador(String nom) {
        jugadors.add(new Jugador(nom));
    }

    public void barrejarOrdre() {
        Collections.shuffle(jugadors);
    }
}