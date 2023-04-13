package personagem.classesFilho;

import personagem.classesPai.Personagem;

public class Suporte extends Personagem {
    private int qtdCuraInicial = 10;
    private int qtdCuraAtual;

    public Suporte(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
        setQtdCuraAtual(this.qtdCuraInicial);
    }

    public int getQtdCuraInicial() {
        return this.qtdCuraInicial;
    }

    public void setQtdCuraAtual(int qtd) {
        this.qtdCuraAtual = qtd;
    }

    public int getQtdCuraAtual() {
        return this.qtdCuraAtual;
    }

    public void curar(Personagem personagem) {
        personagem.setVidaAtual(personagem.getVidaAtual() + 15);
    }

    @Override
    public String toString() {
        return "Você agora é um Suporte." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\nPoder de Cura: " + getQtdCuraAtual() +
                "\n";
    }
}
