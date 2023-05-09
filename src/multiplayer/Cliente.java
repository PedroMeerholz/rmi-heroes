package multiplayer;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private final String host;
    private final int porta;
    private Socket socket;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.conectar();
        Scanner scanner = new Scanner(System.in);
        String mensagem;
        while (true) {
            if(cliente.receberMensagem() == true) {
                mensagem = scanner.nextLine();
                cliente.enviarMensagem(mensagem);
            }
        }
    }

    public Cliente() {
        this.host = "localhost";
        this.porta = 4200;
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

    public void enviarMensagem(String mensagem) {
        try {
            PrintWriter saida = new PrintWriter(this.socket.getOutputStream(), true);
            saida.println(mensagem);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
    }

    public boolean receberMensagem() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            String[] mensagens = (String[]) entrada.readObject();
            for(int i = 0; i < mensagens.length; i++) {
                if(mensagens[i].equals("true")) {
                    return true;
                }
                System.out.println(mensagens[i]);
            }
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
        return false;
    }
}
