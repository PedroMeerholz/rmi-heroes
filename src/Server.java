import prompt.Jogo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Server {
    private final int PORTA = 4200;
    private int qtdJogadores = 0;
    private ArrayList<Socket> sockets = new ArrayList<>();
    private Jogo jogo;
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
                System.out.println("Aguardando o segundo jogador...");
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                saida.println("Aguardando o segundo jogador...");
            } else if(this.qtdJogadores == 2) {
                iniciarJogo();
            }
        }
    }

    private void iniciarJogo() {
        System.out.println("Iniciando jogo...");
        try {
            for (int i = 0; i < this.sockets.size(); i++) {
                Socket socketJogadorAtual = this.sockets.get(i);
                PrintWriter saida = new PrintWriter(socketJogadorAtual.getOutputStream(), true);
                saida.println("Iniciando Jogo...");
                this.jogo = new Jogo();
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        mostrarMensagemInicial();
    }

    private void mostrarMensagemInicial() {
        try {
            for (Socket sockets : sockets) {
                System.out.println(sockets.getPort());

                String mensagem = """
                        Você adentra em um antigo castelo e se depara um Necromancer e três de seus servos...
                        Se esses monstros sairem do castelo a humanidade estará em apuros...
                        Você tem o dever de derrotá-los e evitar que eles escapem!
                        """;
                PrintWriter saida = new PrintWriter(sockets.getOutputStream(), true);
                saida.println(mensagem);
            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
}
