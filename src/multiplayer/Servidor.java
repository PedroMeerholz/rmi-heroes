package multiplayer;

import prompt.Jogo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    private final int porta;
    private ArrayList<Socket> jogadoresConectados;
    private Jogo jogo;

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar_servidor();
        servidor.getJogo().iniciarJogo();
    }

    public Servidor() {
        this.porta = 4200;
        this.jogadoresConectados = new ArrayList<>();
        this.jogo = new Jogo(jogadoresConectados);
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void iniciar_servidor() {
        try(ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("[SERVER] Servidor iniciado");
            System.out.println("[SERVER] Aguardando usuários...");
            while(jogadoresConectados.size() < 2) {
                Socket userSocket = serverSocket.accept();
                jogadoresConectados.add(userSocket);
                System.out.printf("[SERVER] Usuário %d (port: %d) conectado\n", jogadoresConectados.size(), userSocket.getPort());
                System.out.printf("[SERVER] Usuário %d (local port: %d) conectado\n", jogadoresConectados.size(), userSocket.getLocalPort());
                if (jogadoresConectados.size() == 1) System.out.println("[SERVER] Aguardando segundo usuário...");
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public void enviarMensagem(Socket socket, String mensagem) {
        try {
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println(mensagem);
        } catch (Exception execao) {
            execao.printStackTrace();
        }
    }

    public void receberMensagem(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.printf("[JOGADOR %d] %s\n", socket.getPort(), entrada.readLine());
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
    }
}
