package personagem;

import personagem.herois.*;
import personagem.herois.Heroi;
import personagem.npcs.Necromancer;
import personagem.npcs.Npc;
import personagem.npcs.Servo;

import java.util.ArrayList;

public class GerenciadorDePersonagem {
    public static ArrayList<Npc> npcs = new ArrayList<>();
    public static ArrayList<Heroi> herois = new ArrayList<>();
    public static ArrayList<Npc> criarNpcs() {
        npcs.add(new Necromancer(53, 1000, 30, "Necromancer"));
        for (int i = 0; i < 3; i++) {
            String nome = String.format("Servo %d", i+1);
            npcs.add(new Servo(30, 296, 24, nome));
        }
        return npcs;
    }

    public static void criarArqueiro() {
        Arqueiro arqueiro = new Arqueiro(59, 640, 26);
        System.out.println(arqueiro.toString());
        herois.add(arqueiro);
    }

    public static void criarGuerreiro() {
        Guerreiro guerreiro = new Guerreiro(64, 652, 39);
        System.out.println(guerreiro.toString());
        herois.add(guerreiro);
    }

    public static void criarMago() {
        Mago mago = new Mago(53, 590, 21);
        System.out.println(mago.toString());
        herois.add(mago);
    }

    public static void criarSuporte() {
        Suporte suporte = new Suporte(47, 595, 29);
        System.out.println(suporte.toString());
        herois.add(suporte);
    }
}
