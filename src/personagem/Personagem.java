package personagem;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

    public int atacar(Personagem personagemAlvo) {
        int dano = this.ataqueAtual - personagemAlvo.getDefesaAtual();
        ArrayList<String> mensagens = new ArrayList<>();
        if(dano <= 0) {
            mensagens.add("O ataque não causou nenhum dano ao alvo.\n");
        } else {
            personagemAlvo.setVidaAtual(personagemAlvo.getVidaAtual() - dano);
            mensagens.add(String.format("Dano causado: %d\n", dano));
            mensagens.add(String.format("Vida atual do alvo: %d\n\n", personagemAlvo.getVidaAtual()));
        }
        String[] mensagemParaEnvio = mensagens.toArray(new String[0]);
    }

    public void defender() {
        this.adicionarBonusDefesa();
    }

    private void adicionarBonusDefesa() {
        this.defesaAtual += 5;
    }

    public void removerBonusDefesa() {
        this.defesaAtual -= 5;
    }

    private void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
