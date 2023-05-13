package multiplayer;

import prompt.Jogo;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    private final int porta;
    private ArrayList<Jogador> jogadoresConectados;
    private Jogo jogo;

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar_servidor();
        servidor.getJogo().iniciarJogo();
    }

    public Servidor() {
        this.porta = 4200;
        this.jogadoresConectados = new ArrayList<>();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void iniciar_servidor() {
        try(ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("[SERVIDOR] Servidor iniciado");
            System.out.println("[SERVIDOR] Aguardando usuários...");
            while(this.jogadoresConectados.size() < 2) {
                Socket userSocket = serverSocket.accept();
                Jogador jogador = new Jogador(userSocket);
                this.jogadoresConectados.add(jogador);
                System.out.printf("[SERVIDOR] Usuário (port: %d) conectado\n", userSocket.getPort());
                System.out.printf("[SERVIDOR] Usuário (local port: %d) conectado\n", userSocket.getLocalPort());
                if(this.jogadoresConectados.size() == 1) {
                    String[] mensagens = {"[SERVIDOR] Aguardando o outro jogador"};
                    this.enviarMensagem(jogador.getSocket(), mensagens);
                }
            }
            this.jogo = new Jogo(this.jogadoresConectados);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    private void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
