package personagem.herois;

public class Mago extends Heroi {

    public Mago(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    @Override
    public String[] enviarMensagemCriacao() {
        String[] mensagens = {
                "\nVocê é um mago",
                String.format("Vida: %d", getVidaAtual()),
                String.format("Ataque: %d", getAtaqueAtual()),
                String.format("Defesa: %d", getDefesaAtual()),
        };
        return mensagens;
    }

    @Override
    public String classeHeroi() {
        return "Mago";
    }
}
