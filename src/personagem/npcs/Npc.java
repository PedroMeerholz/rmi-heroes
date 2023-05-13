package personagem.npcs;

import personagem.Personagem;
import java.util.Random;

public abstract class Npc extends Personagem {
    private String nome;
    public Npc(int ataque, int vida, int defesa, String nome) {
        super(ataque, vida, defesa);
        this.setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int selecionarAlvo() {
        Random random = new Random();
        return random.nextInt(2);
    }
}
