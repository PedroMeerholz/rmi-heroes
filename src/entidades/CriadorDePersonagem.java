package entidades;

import java.util.ArrayList;

public class CriadorDePersonagem {
    public static ArrayList<Npc> criarNpcs() {
        ArrayList<Npc> npcs = new ArrayList<Npc>();
        npcs.add(new Necromancer(45, 1000, 30));
        for (int i = 0; i < 3; i++) {
            npcs.add(new Servo(12, 296, 24));
        }
        return npcs;
    }

    public static Arqueiro criarArqueiro() {
        return new Arqueiro(59, 640, 26);
    }

    public static Guerreiro criarGuerreiro() {
        return new Guerreiro(64, 652, 39);
    }

    public static Mago criarMago() {
        return new Mago(53, 590, 21);
    }

    public static Suporte criarSuporte() {
        return new Suporte(47, 595, 29);
    }
}
