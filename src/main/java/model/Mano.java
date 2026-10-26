
package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Representa y administra la mano de un participante.
 * Almacena las cartas mediante una lista enlazada y calcula
 * el puntaje, ajustando el valor de los ases cuando es necesario.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */

public class Mano {

    private final LinkedList<Carta> cartas;

    public Mano() {
        cartas = new LinkedList<>();
    }

    public void agregarCarta(Carta carta) {
        if (carta == null) {
            throw new IllegalArgumentException(
                    "No se puede agregar una carta nula."
            );
        }

        cartas.add(carta);
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        int ases = 0;

        for (Carta carta : cartas) {
            puntaje += carta.obtenerValor();

            if (carta.esAs()) {
                ases++;
            }
        }

        while (puntaje > 21 && ases > 0) {
            puntaje -= 10;
            ases--;
        }

        return puntaje;
    }

    public boolean tieneBlackjackInicial() {
        if (cartas.size() != 2) {
            return false;
        }

        boolean tieneAs = false;
        boolean tieneDiezOFigura = false;

        for (Carta carta : cartas) {
            if (carta.esAs()) {
                tieneAs = true;
            }

            if (carta.obtenerValor() == 10) {
                tieneDiezOFigura = true;
            }
        }

        return tieneAs && tieneDiezOFigura;
    }

    public boolean sePaso() {
        return calcularPuntaje() > 21;
    }

    public boolean tieneVeintiuno() {
        return calcularPuntaje() == 21;
    }

    public boolean estaVacia() {
        return cartas.isEmpty();
    }

    public int cantidadCartas() {
        return cartas.size();
    }

    public List<Carta> getCartas() {
        return java.util.Collections.unmodifiableList(
                new LinkedList<>(cartas)
        );
    }

    public void limpiar() {
        cartas.clear();
    }

    @Override
    public String toString() {
        return cartas + " | Puntaje: " + calcularPuntaje();
    }
}
