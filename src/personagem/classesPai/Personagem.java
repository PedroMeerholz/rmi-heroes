package personagem.classesPai;
public abstract class Personagem {
    private int ataqueBase;
    private int defesaBase;
    private int vidaInicial;
    private int ataqueAtual;
    private int defesaAtual;
    private int vidaAtual;
    private boolean vivo;

    public Personagem(int ataque, int vida, int defesa) {
        this.setVidaInicial(vida);
        this.setVidaAtual(this.getVidaInicial());

        this.setAtaqueBase(ataque);
        this.setAtaqueAtual(this.getAtaqueBase());

        this.setDefesaBase(defesa);
        this.setDefesaAtual(this.getDefesaBase());

        this.setVivo(true);
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(int defesaBase) {
        this.defesaBase = defesaBase;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public void setVidaInicial(int vidaInicial) {
        this.vidaInicial = vidaInicial;
    }

    public int getAtaqueAtual() {
        return ataqueAtual;
    }

    public void setAtaqueAtual(int ataqueAtual) {
        this.ataqueAtual = ataqueAtual;
    }

    public int getDefesaAtual() {
        return defesaAtual;
    }

    public void setDefesaAtual(int defesaAtual) {
        this.defesaAtual = defesaAtual;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void atacar(Personagem personagemAlvo) {
        int dano = this.ataqueAtual - personagemAlvo.getDefesaAtual();
        if(dano <= 0) {
            System.out.println("O ataque não causou nenhum dano ao alvo");
        } else {
            personagemAlvo.setVidaAtual(personagemAlvo.getVidaAtual() - dano);
            System.out.println("Dano causado: " + dano);
            System.out.println("Vida atual do alvo: " + personagemAlvo.getVidaAtual());
        }
    }
    public String defender() {
        this.adicionarBonusDefesa();
        return "Irei defender na próxima rodada";
    }

    private void adicionarBonusDefesa() {
        this.defesaAtual += 5;
    }

    public void removerBonusDefesa() {
        this.defesaAtual -= 5;
    }
}
