package multiplayer.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIOManager {
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
            System.out.println(entrada.readLine());
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
