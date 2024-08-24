import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL sistema = new ArvoreAVL();

        // Inserções
        sistema.inserirReserva(new Reserva(1001, "Alice Silva", "VOO1", "2024-08-20T15:30"));
        sistema.inserirReserva(new Reserva(1002, "João Pereira", "VOO1", "2024-08-20T14:00"));
        sistema.inserirReserva(new Reserva(1003, "Maria Costa", "VOOO1", "2024-08-20T15:00"));
        sistema.inserirReserva(new Reserva(1004, "Carlos Souza", "VOOO2", "2024-08-20T09:00"));
        sistema.inserirReserva(new Reserva(1005, "Beatriz Lima", "VOOO2", "2024-08-21T10:30"));
        sistema.inserirReserva(new Reserva(1006, "Fernanda Oliveira", "VOOO1", "2024-08-20T16:00"));
        sistema.inserirReserva(new Reserva(1007, "Joaquim Ferreira", "VOOO2", "2024-08-22T16:00"));
        sistema.inserirReserva(new Reserva(1008, "Manoel Leao", "VOOO1", "2024-08-20T16:00"));
        sistema.inserirReserva(new Reserva(1009, "Jose Carlos", "VOOO2", "2024-08-21T10:30"));
        sistema.inserirReserva(new Reserva(1010, "Andre Mateus", "VOOO1", "2024-08-20T16:00"));

        // Remoções
        sistema.removerReserva(1009);
        sistema.removerReserva(1010);
        sistema.removerReserva(1004);

        // Impressão em pré-ordem
        sistema.imprimirEmPreOrdem();

        /*sistema.imprimirEmPosOrdem();
        sistema.imprimirEmOrdem();*/

        /*System.out.println(sistema.buscarReserva(1001));*/


        /*String voo = "999";
        List <Reserva> reservasVooInexistente = sistema.listarReservasPorVoo(voo);

        if(!reservasVooInexistente.isEmpty()){
            System.out.println("Reservas para o voo "+ voo +": ");
            for (Reserva r: reservasVooInexistente){
                System.out.println(r);
            }
        }else{
            System.out.println("Nenhuma reserva encontrada para o voo "+ voo +".");
        }

        String voo1 = "VOO1";
        List <Reserva> reservasVooVOO1 = sistema.listarReservasPorVoo(voo1);

        if(!reservasVooVOO1.isEmpty()){
            System.out.println("Reservas para o voo "+ voo1 +": ");
            for (Reserva r: reservasVooVOO1){
                System.out.println(r);
            }
        }else{
            System.out.println("Nenhuma reserva encontrada para o voo "+ voo +".");
        }
        */
    }
}
