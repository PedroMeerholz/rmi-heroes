import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private final String HOST = "localhost";
    private final int PORTA = 4200;
    private Socket socket;

    public static void main(String[] args) {
        Client client = new Client();
        client.conectarComOServidor();
        while (true) {
            client.esperarRespostaDoServidor();
        }
    }

    public void conectarComOServidor() {
        System.out.println("Conectando com o servidor...");
        try {
            this.socket = new Socket(HOST, PORTA);
            System.out.println("Conectado com sucesso");
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void esperarRespostaDoServidor() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String linha;
            while((linha = entrada.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
}
