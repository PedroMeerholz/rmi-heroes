package turno.resultadoAtaque;

public class ResultadoAtaque {
    private String personagemAtacante;
    private String personagemAlvo;
    private int dano;
    private int vidaAtualAlvo;

    public String getPersonagemAtacante() {
        return personagemAtacante;
    }

    public void setPersonagemAtacante(String personagemAtacante) {
        this.personagemAtacante = personagemAtacante;
    }

    public String getPersonagemAlvo() {
        return personagemAlvo;
    }

    public void setPersonagemAlvo(String personagemAlvo) {
        this.personagemAlvo = personagemAlvo;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getVidaAtualAlvo() {
        return vidaAtualAlvo;
    }

    public void setVidaAtualAlvo(int vidaAtualAlvo) {
        this.vidaAtualAlvo = vidaAtualAlvo;
    }
}
