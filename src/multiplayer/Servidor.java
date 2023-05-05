package multiplayer;

import prompt.Jogo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private final int porta;
    private Socket jogadorConectado;
    private Jogo jogo;

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciar_servidor();
        servidor.getJogo().iniciarJogo();
    }

    public Servidor() {
        this.porta = 4200;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void iniciar_servidor() {
        try(ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("[SERVER] Servidor iniciado");
            System.out.println("[SERVER] Aguardando usuários...");
            while(jogadorConectado == null) {
                Socket userSocket = serverSocket.accept();
                jogadorConectado = userSocket;
                System.out.printf("[SERVER] Usuário (port: %d) conectado\n", userSocket.getPort());
                System.out.printf("[SERVER] Usuário (local port: %d) conectado\n", userSocket.getLocalPort());
                this.jogo = new Jogo(this.jogadorConectado);
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
