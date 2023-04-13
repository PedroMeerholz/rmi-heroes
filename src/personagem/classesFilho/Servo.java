package personagem.classesFilho;

import personagem.classesPai.Npc;

public class Servo extends Npc {
    public Servo(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String toString() {
        return "Um servo do necromancer surgiu." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\n";
    }
}
