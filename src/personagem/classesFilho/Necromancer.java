package personagem.classesFilho;

import personagem.classesPai.Npc;

public class Necromancer extends Npc {
    public Necromancer(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String toString() {
        return "Um necromancer surgiu." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\n";
    }
}
