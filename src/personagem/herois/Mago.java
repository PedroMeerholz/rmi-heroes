package personagem.herois;

public class Mago extends Heroi {

    public Mago(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String toString() {
        return "Você agora é um Mago." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\n";
    }

    @Override
    public String classeHeroi() {
        return "Mago";
    }
}
