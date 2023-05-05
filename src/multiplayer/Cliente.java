package multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private final String host;
    private final int porta;
    private Socket socket;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.conectar();
        while (true) {
            cliente.receberMensagem(cliente.socket);
        }
    }

    public Cliente() {
        this.host = "localhost";
        this.porta = 4200;
    }

    public String getHost() {
        return this.host;
    }

    public int getPorta() {
        return this.porta;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void conectar() {
        try {
            this.socket = new Socket(this.host, this.porta);
            System.out.println("[JOGADOR] Conectado com sucesso");
            System.out.printf("[JOGADOR] Port: %d\n", socket.getPort());
            System.out.printf("[JOGADOR] Local port: %d\n", socket.getLocalPort());
        } catch (Exception excecao) {
            excecao.printStackTrace();
        }
    }

    public void enviarMensagem(Socket socket, String mensagem) {
        try {
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println(mensagem);
        } catch(Exception exception) {

        }
    }

    public void receberMensagem(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println(entrada.readLine());

            String linha;
            while((linha = entrada.readLine()) != null) {
                System.out.println(linha);
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
