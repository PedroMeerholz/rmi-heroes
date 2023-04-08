package entidades;

public class Mago extends Personagem {

    public Mago(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String toString() {
        return "Você agora é um Mago." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual();
    }
}
