package model;

/**
 * Representa al jugador.
 *
 * @author Anderson Romero
 * @author Javier Patiño
 */
public class Jugador {

    private final String nombre;
    private int saldo;
    private int apuestaActual;
    private final Mano mano;

    public Jugador(String nombre, int saldoInicial) {
        this.nombre = nombre;
        this.saldo = saldoInicial;
        this.apuestaActual = 0;
        this.mano = new Mano();
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getApuestaActual() {
        return apuestaActual;
    }

    public Mano getMano() {
        return mano;
    }

    public boolean apuestaEsValida(int apuesta) {
        return apuesta >= 10
                && apuesta % 10 == 0
                && apuesta <= saldo;
    }

    public boolean realizarApuesta(int apuesta) {
        if (!apuestaEsValida(apuesta)) {
            return false;
        }

        saldo -= apuesta;
        apuestaActual = apuesta;
        return true;
    }

    public void recibirCarta(Carta carta) {
        mano.agregarCarta(carta);
    }

    public void acreditarSaldo(int cantidad) {
        saldo += cantidad;
    }

    public void limpiarMano() {
        mano.limpiar();
    }
}