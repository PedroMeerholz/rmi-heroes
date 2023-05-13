package personagem;

import personagem.herois.Arqueiro;
import personagem.herois.Escudeiro;
import personagem.herois.Guerreiro;
import personagem.herois.Mago;
import personagem.npcs.Necromancer;
import personagem.npcs.Npc;
import personagem.npcs.Servo;

import java.util.ArrayList;

public class GerenciadorDePersonagem {
    public static ArrayList<Npc> npcs = new ArrayList<>();

    public static ArrayList<Npc> criarNpcs() {
        npcs.add(new Necromancer(70, 1000, 30, "Necromancer"));
        for (int i = 0; i < 3; i++) {
            String nome = String.format("Servo %d", i+1);
            npcs.add(new Servo(30, 296, 24, nome));
        }
        return npcs;
    }

    public Arqueiro criarArqueiro() {
        return new Arqueiro(59, 640, 26);
    }

    public Guerreiro criarGuerreiro() {
        return new Guerreiro(64, 652, 39);
    }

    public Mago criarMago() {
        return new Mago(53, 590, 21);
    }

    public Escudeiro criarEscudeiro() {
        return new Escudeiro(47, 500, 69);
    }
}
