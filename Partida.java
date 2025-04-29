package uno;

import uno.interficie.UI;
import uno.logica.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    Scanner entrada = new Scanner(System.in);
    OrdreJugador ordrejugador = new OrdreJugador();
    Mazo mazo = new Mazo();
    Pilo pilo = new Pilo();

    public void jugar() {
        preparar();
        repartirCartes();
        torn();
    }

    public void preparar() {
        mazo.barrejar();
        System.out.println("Quants jugadors jugaran?");
        int NombreJugadors = entrada.nextInt();

        for (int i = 0; i < NombreJugadors; i++) {
            System.out.println("Nom del jugador " + (i+1) + ":");
            String nom = entrada.next();
            ordrejugador.crearJugador(nom);
        }

        ordrejugador.barrejarOrdre();

        // Col·loca la primera carta al pilo
        Carta primeraCarta = mazo.agafarCarta();
        pilo.addCarta(primeraCarta);
        System.out.println("Carta que comença la partida: ");
        UI.mostrarCarta(primeraCarta);
    }

    public void repartirCartes() {
        for (int i = 0; i < 7; i++) {
            for (Jugador jugador : ordrejugador.jugadors) {
                jugador.robarCarta(mazo);
            }
        }
    }



    public void torn() {
        boolean partidaAcabada = false;

        while (!partidaAcabada) {
            Jugador jugadorActual = ordrejugador.getJugadorActiu();
            System.out.println("\nTorn de: " + jugadorActual.getNom());

            // Mostrar carta superior del pilo
            Carta cartaSuperior = pilo.consultarCarta();
            System.out.println("Carta superior:");
            UI.mostrarCarta(cartaSuperior);

            // Mostrar cartes del jugador
            System.out.println("Les teves cartes:");
            UI.mostrarCartes(jugadorActual.getCartes());

            // Comprovar si pot tirar alguna carta
            if (jugadorActual.potTirarCarta(cartaSuperior)) {
                System.out.println("Quina crata vols tirar?");
                int indexCarta = entrada.nextInt();

                if (indexCarta >= 0 && indexCarta < jugadorActual.getCartes().size()) {
                    Carta cartaATirar = jugadorActual.getCartes().get(indexCarta);

                    if (Regles.sonCartesCompatibles(cartaATirar, cartaSuperior)) {
                        jugadorActual.tirarCarta(cartaATirar, pilo);

                        // Comprovar si ha guanyat
                        if (jugadorActual.nombreDeCartes() == 0) {
                            System.out.println(jugadorActual.getNom() + " ha guanyat la partida!");
                            partidaAcabada = true;
                            continue;
                        }
                    } else {
                        System.out.println("No pots tirar aquesta carta.");
                        continue;
                    }
                } else {
                    System.out.println("Index no correcte, torna a provar.");
                    continue;
                }
            } else {
                System.out.println("No tens cap carta valida. s'ha robat una carta.");
                jugadorActual.robarCarta(mazo);

                Carta cartaRobada = jugadorActual.getCartes().get(jugadorActual.getCartes().size() - 1);
                if (Regles.sonCartesCompatibles(cartaRobada, cartaSuperior)) {
                    UI.mostrarCarta(cartaRobada);
                    jugadorActual.tirarCarta(cartaRobada, pilo);
                }
            }

            if (mazo.esBuit()) {
                mazo.reiniciar(pilo);
            }

            ordrejugador.passarTorn();
        }
    }
}