import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import socket.SocketIOManager;

public class Server {
    private final int PORTA = 4200;
    private int qtdJogadores = 0;
    private ArrayList<Socket> sockets = new ArrayList<>();
    private SocketIOManager socketIOManager = new SocketIOManager();;
    public static void main(String[] args) {
        Server server = new Server();
        server.iniciarServidor();
    }

    public void iniciarServidor() {
        System.out.println("Iniciando o servidor...");
        try (ServerSocket serverSocket = new ServerSocket(PORTA)){
            System.out.println("Servidor iniciado com sucesso...");
            System.out.println("Aguardando jogadores...");
            this.aguardarConexao(serverSocket);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    private void aguardarConexao(ServerSocket serverSocket) throws IOException {
        while (this.qtdJogadores < 2) {
            Socket socket = serverSocket.accept();
            this.sockets.add(socket);
            this.qtdJogadores += 1;
            System.out.printf("Jogador %d aceito\n", this.qtdJogadores);
            if(this.qtdJogadores == 1) {
                String mensagem = "Aguardando o segundo jogador...";
                System.out.println(mensagem);
                socketIOManager.enviarMensagemParaOCliente(socket, mensagem);
            } else if(this.qtdJogadores == 2) {
                iniciarJogo();
            }
        }
    }

    private void iniciarJogo() {
        String mensagem = "Iniciando Jogo...";
        System.out.println(mensagem);
        try {
            for (Socket socket : this.sockets) {
                socketIOManager.enviarMensagemParaOCliente(socket, mensagem);
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        mostrarMensagemInicial();
    }

    private void mostrarMensagemInicial() {
        try {
            for (Socket socket : sockets) {
                System.out.println(socket.getPort());

                String mensagem = """
                        Você adentra em um antigo castelo e se depara um Necromancer e três de seus servos...
                        Se esses monstros sairem do castelo a humanidade estará em apuros...
                        Você tem o dever de derrotá-los e evitar que eles escapem!
                        """;
                socketIOManager.enviarMensagemParaOCliente(socket, mensagem);
            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
}
