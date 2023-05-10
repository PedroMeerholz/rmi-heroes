package personagem;

import personagem.herois.*;
import personagem.herois.Heroi;
import personagem.npcs.Necromancer;
import personagem.npcs.Npc;
import personagem.npcs.Servo;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class GerenciadorDePersonagem {
    public static ArrayList<Npc> npcs = new ArrayList<>();
    public static ArrayList<Heroi> herois = new ArrayList<>();
    private Socket jogadorConectado;
    private String[] mensagens;

    public GerenciadorDePersonagem(Socket jogadorConectado) {
        this.jogadorConectado = jogadorConectado;
    }
    public static ArrayList<Npc> criarNpcs() {
        npcs.add(new Necromancer(53, 1000, 30, "Necromancer"));
        for (int i = 0; i < 3; i++) {
            String nome = String.format("Servo %d", i+1);
            npcs.add(new Servo(30, 296, 24, nome));
        }
        return npcs;
    }

    public void criarArqueiro() {
        Arqueiro arqueiro = new Arqueiro(59, 640, 26);
        herois.add(arqueiro);
        this.mensagens = arqueiro.enviarMensagemCriacao();
        this.enviarMensagem(this.jogadorConectado, this.mensagens);
    }

    public void criarGuerreiro() {
        Guerreiro guerreiro = new Guerreiro(64, 652, 39);
        herois.add(guerreiro);
        this.mensagens = guerreiro.enviarMensagemCriacao();
        this.enviarMensagem(this.jogadorConectado, this.mensagens);
    }

    public void criarMago() {
        Mago mago = new Mago(53, 590, 21);
        herois.add(mago);
        this.mensagens = mago.enviarMensagemCriacao();
        this.enviarMensagem(this.jogadorConectado, this.mensagens);
    }

    public void criarEscudeiro() {
        Escudeiro escudeiro = new Escudeiro(47, 500, 69);
        herois.add(escudeiro);
        this.mensagens = escudeiro.enviarMensagemCriacao();
        this.enviarMensagem(this.jogadorConectado, this.mensagens);
    }

    private void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
    }
}
