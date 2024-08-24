public class NoAVL {
    Reserva reserva;
    NoAVL esquerda;
    NoAVL direita;
    int altura;

    public NoAVL(Reserva reserva) {
        this.reserva = reserva;
        this.altura = 1;
    }
}
