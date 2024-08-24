import java.time.LocalDateTime;

public class Reserva {
    private int codigo;
    private String nomePassageiro;
    private String voo;
    private LocalDateTime horarioPartida;

    public Reserva(int codigo, String nomePassageiro, String voo, String horarioPartida) {
        this.codigo = codigo;
        this.nomePassageiro = nomePassageiro;
        this.voo = voo;
        this.horarioPartida = LocalDateTime.parse(horarioPartida);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getVoo() {
        return voo;
    }

    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    @Override
    public String toString() {
        return "ID = " + codigo + ", Passageiro = " + nomePassageiro + ", Horário = " + horarioPartida;
    }
}
