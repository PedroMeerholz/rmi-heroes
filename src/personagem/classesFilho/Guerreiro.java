package personagem.classesFilho;

import personagem.classesPai.Personagem;

public class Guerreiro extends Personagem {

    public Guerreiro(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String toString() {
        return "Você é um guerreiro." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\n";
    }
}
