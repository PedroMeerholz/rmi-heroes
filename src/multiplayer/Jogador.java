package multiplayer;

import personagem.herois.Heroi;

import java.net.Socket;

public class Jogador {
    private Heroi heroi;
    private Socket socket;

    public Jogador(Socket socket) {
        this.socket = socket;
    }

    public Heroi getHeroi() {
        return heroi;
    }

    public void setHeroi(Heroi heroi) {
        this.heroi = heroi;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
