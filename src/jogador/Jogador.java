package jogador;

import java.net.Socket;

public class Jogador {
    private int id;

    private Socket socket;

    public Jogador(int id, Socket socket) {
        this.setId(id);
        this.setSocket(socket);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
