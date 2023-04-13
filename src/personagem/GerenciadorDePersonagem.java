package personagem;

import personagem.classesFilho.*;
import personagem.classesPai.Npc;
import personagem.classesPai.Personagem;

import java.util.ArrayList;

public class GerenciadorDePersonagem {
    public static ArrayList<Npc> npcs = new ArrayList<>();
    public static ArrayList<Personagem> herois = new ArrayList<>();
    public static ArrayList<Npc> criarNpcs() {
        npcs.add(new Necromancer(45, 1000, 30));
        for (int i = 0; i < 3; i++) {
            npcs.add(new Servo(12, 296, 24));
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
