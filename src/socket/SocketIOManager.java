package socket;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketIOManager {
    public void enviarMensagemParaOCliente(Socket socket, String mensagem) {
        try {
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println(mensagem);
        } catch(Exception erro) {
            erro.printStackTrace();
        }
    }

    public void lerMensagemDoCliente(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            entrada.readLine();
        } catch(Exception erro) {
            erro.printStackTrace();
        }
    }

    public void lerMensagemDoServidor(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linha;
            while((linha = entrada.readLine()) != null) {
                System.out.println(linha);
            }
        } catch(Exception erro) {
            erro.printStackTrace();
        }
    }

    public void enviarMensagemParaOServidor(Socket socket, String mensagem) {
        try {
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println(mensagem);
        } catch(Exception erro) {
            erro.printStackTrace();
        }
    }
}