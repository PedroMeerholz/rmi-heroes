package personagem.herois;

public class Escudeiro extends Heroi {
    public Escudeiro(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String[] enviarMensagemCriacao() {
        String[] mensagens = {
                "\nVocê é um escudeiro",
                String.format("Vida: %d", getVidaAtual()),
                String.format("Ataque: %d", getAtaqueAtual()),
                String.format("Defesa: %d", getDefesaAtual()),
        };
        return mensagens;
    }

    @Override
    public String classeHeroi() {
        return "Escudeiro";
    }
}
