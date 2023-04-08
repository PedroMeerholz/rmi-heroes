package entidades;

import java.util.Random;

public abstract class Npc extends Personagem {
    public Npc(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    public int selecionarAlvo() {
        Random random = new Random();
        return random.nextInt(1);
    }
}
