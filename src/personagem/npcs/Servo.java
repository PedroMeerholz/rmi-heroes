package personagem.npcs;

import personagem.npcs.Npc;

public class Servo extends Npc {
    public Servo(int ataque, int vida, int defesa, String nome) {
        super(ataque, vida, defesa, nome);
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
