import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArvoreAVL {
    private NoAVL raiz;

    private int altura(NoAVL no) {
        return no == null ? 0 : no.altura;
    }

    private int getBalanceamento(NoAVL no) {
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }


    public void inserirReserva(Reserva reserva) {
        raiz = inserirNo(raiz, reserva);
    }

    private NoAVL inserirNo(NoAVL no, Reserva reserva) {
        if (no == null) {
            return new NoAVL(reserva);
        }

        if (reserva.getCodigo() < no.reserva.getCodigo()) {
            no.esquerda = inserirNo(no.esquerda, reserva);
        } else if (reserva.getCodigo() > no.reserva.getCodigo()) {
            no.direita = inserirNo(no.direita, reserva);
        } else {
            return no;
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1 && reserva.getCodigo() < no.esquerda.reserva.getCodigo()) {
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && reserva.getCodigo() > no.direita.reserva.getCodigo()) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento > 1 && reserva.getCodigo() > no.esquerda.reserva.getCodigo()) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && reserva.getCodigo() < no.direita.reserva.getCodigo()) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }


    private NoAVL rotacaoDireita(NoAVL noRaiz) {
        NoAVL noNovoRaiz = noRaiz.esquerda;
        NoAVL subarvoreDireita = noNovoRaiz.direita;

        noNovoRaiz.direita = noRaiz;
        noRaiz.esquerda = subarvoreDireita;

        noRaiz.altura = Math.max(altura(noRaiz.esquerda), altura(noRaiz.direita)) + 1;
        noNovoRaiz.altura = Math.max(altura(noNovoRaiz.esquerda), altura(noNovoRaiz.direita)) + 1;

        return noNovoRaiz;
    }

    private NoAVL rotacaoEsquerda(NoAVL noRaiz) {
        NoAVL noNovoRaiz = noRaiz.direita;
        NoAVL subarvoreEsquerda = noNovoRaiz.esquerda;

        noNovoRaiz.esquerda = noRaiz;
        noRaiz.direita = subarvoreEsquerda;

        noRaiz.altura = Math.max(altura(noRaiz.esquerda), altura(noRaiz.direita)) + 1;
        noNovoRaiz.altura = Math.max(altura(noNovoRaiz.esquerda), altura(noNovoRaiz.direita)) + 1;

        return noNovoRaiz;
    }

    public Reserva buscarReserva(int codigo) {
        NoAVL no = buscarNo(raiz, codigo);
        return no == null ? null : no.reserva;
    }

    private NoAVL buscarNo(NoAVL no, int codigo) {
        if (no == null || no.reserva.getCodigo() == codigo) {
            return no;
        }

        if (codigo < no.reserva.getCodigo()) {
            return buscarNo(no.esquerda, codigo);
        } else {
            return buscarNo(no.direita, codigo);
        }
    }


    public void removerReserva(int codigo) {
        raiz = removerNo(raiz, codigo);
    }

    private NoAVL removerNo(NoAVL no, int codigo) {
        if (no == null) {
            return no;
        }
        if (codigo < no.reserva.getCodigo()) {
            no.esquerda = removerNo(no.esquerda, codigo);
        } else if (codigo > no.reserva.getCodigo()) {
            no.direita = removerNo(no.direita, codigo);
        } else {
            if ((no.esquerda == null) || (no.direita == null)) {
                NoAVL temp = null;
                if (temp == no.esquerda) {
                    temp = no.direita;
                } else {
                    temp = no.esquerda;
                }

                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAVL temp = menorValorNo(no.direita);
                no.reserva = temp.reserva;
                no.direita = removerNo(no.direita, temp.reserva.getCodigo());
            }

        }

        if (no == null) {
            return no;
        }

        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;

        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1 && getBalanceamento(no.esquerda) >= 0) {
            return rotacaoDireita(no);
        }

        if (balanceamento > 1 && getBalanceamento(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && getBalanceamento(no.direita) <= 0) {
            return rotacaoEsquerda(no);
        }

        if (balanceamento < -1 && getBalanceamento(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoAVL menorValorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public List<Reserva> listarReservasPorVoo(String voo) {
        List<Reserva> reservas = new ArrayList<>();
        coletarReservasPorVoo(raiz, voo, reservas);
        reservas.sort(Comparator.comparing(Reserva::getHorarioPartida));
        return reservas;
    }

    private void coletarReservasPorVoo(NoAVL no, String voo, List<Reserva> reservas) {
        if (no != null) {
            coletarReservasPorVoo(no.esquerda, voo, reservas);
            if (no.reserva.getVoo().equals(voo)) {
                reservas.add(no.reserva);
            }
            coletarReservasPorVoo(no.direita, voo, reservas);
        }
    }

    public void imprimirEmPreOrdem() {
        imprimirEmPreOrdem(raiz);
    }

    private void imprimirEmPreOrdem(NoAVL no) {
        if (no != null) {
            System.out.println(no.reserva);
            imprimirEmPreOrdem(no.esquerda);
            imprimirEmPreOrdem(no.direita);
        }
    }

    public void imprimirEmPosOrdem() {
        imprimirEmPosOrdem(raiz);
    }

    private void imprimirEmPosOrdem(NoAVL no) {
        if (no != null) {
            imprimirEmPosOrdem(no.esquerda);
            imprimirEmPosOrdem(no.direita);
            System.out.println(no.reserva);
        }
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdem(raiz);
    }

    private void imprimirEmOrdem(NoAVL no) {
        if (no != null) {
            imprimirEmOrdem(no.esquerda);
            System.out.println(no.reserva);
            imprimirEmOrdem(no.direita);
        }
    }
}
