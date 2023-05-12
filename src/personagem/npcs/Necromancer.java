package personagem.npcs;

public class Necromancer extends Npc {
    public Necromancer(int ataque, int vida, int defesa, String nome) {
        super(ataque, vida, defesa, nome);
    }

    @Override
    public String toString() {
        return "Um necromancer surgiu." +
                "\nVida: " + getVidaAtual() +
                "\nAtaque: " + getAtaqueAtual() +
                "\nDefesa: " + getDefesaAtual() +
                "\n";
    }
}
