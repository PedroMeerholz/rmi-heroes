package personagem.herois;

public class Suporte extends Heroi {
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

    public void curar(Heroi heroi) {
        heroi.setVidaAtual(heroi.getVidaAtual() + 15);
    }

    @Override
    public String[] enviarMensagemCriacao() {
        String[] mensagens = {
                "\nVocê é um suporte",
                String.format("Vida: %d", getVidaAtual()),
                String.format("Ataque: %d", getAtaqueAtual()),
                String.format("Defesa: %d", getDefesaAtual()),
        };
        return mensagens;
    }

    @Override
    public String classeHeroi() {
        return "Suporte";
    }
}
