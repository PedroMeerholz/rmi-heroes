package personagem.herois;

public class Arqueiro extends Heroi {

    public Arqueiro(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String[] enviarMensagemCriacao() {
        String[] mensagens = {
                "\nVocê é um arqueiro",
                String.format("Vida: %d", getVidaAtual()),
                String.format("Ataque: %d", getAtaqueAtual()),
                String.format("Defesa: %d", getDefesaAtual()),
        };
        return mensagens;
    }

    @Override
    public String classeHeroi() {
        return "Arqueiro";
    }
}
