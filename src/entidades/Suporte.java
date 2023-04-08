package entidades;
public class Suporte extends Personagem {
    private int qtdCuraInicial;
    private int qtdCuraAtual;

    public Suporte(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
        setQtdCuraInicial(10);
        setQtdCuraAtual(this.qtdCuraInicial);
    }

    public void setQtdCuraInicial(int qtd) {
        this.qtdCuraInicial = qtd;
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

    public String curar(Personagem personagem) {
        return "Um personagem foi curado";
    }

    @Override
    public String toString() {
        return "Você agora é um Suporte." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\nPoder de Cura: " + getQtdCuraAtual();
    }
}
