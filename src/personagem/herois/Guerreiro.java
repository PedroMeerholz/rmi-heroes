package personagem.herois;

public class Guerreiro extends Heroi {

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

    @Override
    public String classeHeroi() {
        return "Guerreiro";
    }
}
