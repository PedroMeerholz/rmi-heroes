package personagem.herois;

import personagem.Personagem;

public abstract class Heroi extends Personagem {
    public Heroi(int ataque, int vida, int defesa) {
        super(ataque, vida, defesa);
    }

    public abstract String classeHeroi();
}
