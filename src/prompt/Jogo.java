package prompt;

import multiplayer.Jogador;
import personagem.GerenciadorDePersonagem;
import turno.resultadoAtaque.ResultadoAtaque;
import personagem.herois.Heroi;
import personagem.npcs.Npc;
import personagem.Personagem;
import turno.Turno;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Jogo {
    private ArrayList<Npc> npcs;
    private ArrayList<Personagem> heroisComBonusDeDefesa;
    private ArrayList<Jogador> jogadoresConectados;
    private Turno turno = new Turno();

    public Jogo(ArrayList<Jogador> jogadoresConectados) {
        GerenciadorDePersonagem.criarNpcs();
        this.npcs = GerenciadorDePersonagem.npcs;
        this.heroisComBonusDeDefesa = new ArrayList<>();
        this.jogadoresConectados = jogadoresConectados;
    }

    private void mostrarMensagemInicial() {
        String[] mensagens = {
                "[SERVIDOR] Você adentra em um castelo antigo e se deparou um Necromancer e três de seus servos... ",
                "[SERVIDOR] Esses monstros jamais podem sair do castelo, ou a humanidade estará em apuros...",
                "[SERVIDOR] Você tem o dever de derrotá-los e evitar que eles escapem!"
        };
        this.enviarMensagemParaTodos(mensagens);
    }

    public void iniciarJogo() {
        SelecaoPersonagem selecaoPersonagem = new SelecaoPersonagem(this.jogadoresConectados);
        this.mostrarMensagemInicial();
        for (Jogador jogador : this.jogadoresConectados) {
            selecaoPersonagem.inicarSelecao(jogador);
        }
        this.executarJogo();
    }

    private void executarJogo() {
        do {
            for (Jogador jogador : this.jogadoresConectados) {
                if(jogador.getHeroi().isVivo()) {
                    this.executarTurnoDoJogador(this.turno.getTurno(), jogador);
                    this.executarTurnoDosNpcs();
                    this.removerBonusDeDefesaExistente();
                    this.verificarPersonagensVivos();
                    this.exibirMensagemTurnoProximoJogador(jogador);
                }
            }
            this.turno.avancarTurno();
        } while (!this.npcs.isEmpty() || (this.jogadoresConectados.get(0).getHeroi().isVivo() || this.jogadoresConectados.get(1).getHeroi().isVivo()));
    }

    private void exibirMensagemTurnoProximoJogador(Jogador jogadorConectado) {
        String[] mensagens = {"\nVez do outro jogador"};
        this.enviarMensagem(jogadorConectado.getSocket(), mensagens);
    }

    private void executarTurnoDoJogador(int turno, Jogador jogadorConectado) {
        int opcao;
        String[] mensagens = {
                String.format("\nSeu turno -> Turno %d", turno),
                "Que ação você deseja fazer? ",
                "1 - Atacar",
                "2 - Defender",
                "Digite aqui: ",
                "true"
        };
        do {
            this.enviarMensagem(jogadorConectado.getSocket(), mensagens);
            opcao = this.receberMensagem(jogadorConectado.getSocket());
        } while (this.verificaOpcaoDoTurnoDoJogador(opcao, jogadorConectado) == false);
    }

    private boolean verificaOpcaoDoTurnoDoJogador(int opcao, Jogador jogadorConectado) {
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            this.acionaAcaoTurnoDoJogador(opcao, jogadorConectado);
            return true;
        }
    }

    private void acionaAcaoTurnoDoJogador(int opcao, Jogador jogadorConectado) {
        Heroi heroi = jogadorConectado.getHeroi();
        if(opcao == 1) {
            // atacar
            int alvoIdx = selecionarAlvo(jogadorConectado.getSocket());
            alvoIdx -= 1;
            Npc npcAlvo = npcs.get(alvoIdx);
            ResultadoAtaque resultadoAtaque = heroi.atacar(npcAlvo);
            verificarResultadoAtaqueHeroi(resultadoAtaque, jogadorConectado.getSocket());
            verificarVidaNpcAlvo(npcAlvo);
        } else {
            // defender
            jogadorConectado.getHeroi().defender();
            heroisComBonusDeDefesa.add(heroi);
        }
    }

    private void verificarResultadoAtaqueHeroi(ResultadoAtaque resultadoAtaque, Socket jogadorConectado) {
        ArrayList<String> listaMensagens = new ArrayList<>();
        if(resultadoAtaque.getDano() == 0) {
            listaMensagens.add("O ataque não causou nenhum dano ao alvo.");
        } else {
            listaMensagens.add(String.format("Dano causado: %d", resultadoAtaque.getDano()));
            if(resultadoAtaque.getVidaAtualAlvo() <= 0){
                listaMensagens.add(String.format("O alvo foi abatido"));
            }else{
                listaMensagens.add(String.format("Vida atual do alvo: %d\n", resultadoAtaque.getVidaAtualAlvo()));
            }

        }
        String[] mensagens = listaMensagens.toArray(new String[0]);
        this.enviarMensagem(jogadorConectado, mensagens);
    }

    private int selecionarAlvo(Socket jogadorConectado) {
        int alvo;
        ArrayList<String> listaMensagens = new ArrayList<>();
        do {
            listaMensagens.add("Selecione seu alvo abaixo");
            for(int i = 0; i < npcs.size(); i++){
                if(i == 0) {
                    listaMensagens.add("1 - Necromancer");
                } else {
                    listaMensagens.add(String.format("%d - %d° Servo", i+1, i));
                }
            }
            listaMensagens.add("Digite aqui:");
            listaMensagens.add("true");
            String[] mensagensParaEnvio = listaMensagens.toArray(new String[0]);
            this.enviarMensagem(jogadorConectado, mensagensParaEnvio);
            alvo = this.receberMensagem(jogadorConectado);
        } while(verificarOpcaoDeAlvo(alvo) == false);
        return alvo;
    }

    private boolean verificarOpcaoDeAlvo(int alvo) {
        if(alvo < 1 || alvo > 4) {
            return false;
        } else {
            return true;
        }
    }

    private void removerBonusDeDefesaExistente() {
        if(heroisComBonusDeDefesa.size() > 0) {
            for (int i = 0; i < heroisComBonusDeDefesa.size(); i++) {
                heroisComBonusDeDefesa.get(i).removerBonusDefesa();
            }
        }
        heroisComBonusDeDefesa.clear();
    }

    private void executarTurnoDosNpcs() {
        Npc npc;
        for(int i = 0; i < this.npcs.size(); i++) {
            npc = this.npcs.get(i);
            int alvo = npc.selecionarAlvo();
            Jogador jogadorAlvo = this.jogadoresConectados.get(alvo);
            ResultadoAtaque resultadoAtaque = this.atacarHeroiAlvo(this.npcs.get(i), jogadorAlvo.getHeroi());
            String[] mensagens = {
                    String.format("%s atacou o %s.", resultadoAtaque.getPersonagemAtacante(), resultadoAtaque.getPersonagemAlvo())
            };
            this.enviarMensagemParaTodos(mensagens);
        }
    }

    private ResultadoAtaque atacarHeroiAlvo(Npc npc, Heroi heroi) {
        ResultadoAtaque resultadoAtaque = new ResultadoAtaque();
        resultadoAtaque.setPersonagemAtacante(npc.getNome());
        resultadoAtaque.setPersonagemAlvo(heroi.classeHeroi());
        npc.atacar(heroi);
        if(heroi.getVidaAtual() <= 0) {
            heroi.setVivo(false);
        }
        return resultadoAtaque;
    }

    private void verificarPersonagensVivos() {
        verificarHeroisVivos();
        verificarNpcsVivos();
    }

    private void verificarHeroisVivos() {
        if (!jogadoresConectados.get(0).getHeroi().isVivo() && !jogadoresConectados.get(1).getHeroi().isVivo()) {
            String[] mensagens = {
                    "Você foi derrotado e o Necromancer escapou...",
                    "A humanidade sofrerá por um longo tempo!",
                    "==== FIM DE JOGO ====",
                    "exit"
            };
            this.enviarMensagemParaTodos(mensagens);
            System.exit(0);
        }
    }

    private void verificarNpcsVivos() {
        if (!npcs.get(0).isVivo()) {
            String[] mensagens = {
                    "Você derrotou o necromancer e seus servos...",
                    "A humanidade está a salvo e vocês foram reconhecidos como heróis!",
                    "==== FIM DE JOGO ====",
                    "exit"
            };
            this.enviarMensagemParaTodos(mensagens);
            System.exit(0);
        }
    }

    private void verificarVidaNpcAlvo(Npc npcAlvo) {
        if(npcAlvo.getVidaAtual() <= 0) {
            npcAlvo.setVivo(false);
            if(npcAlvo.getNome() != "Necromancer") {
                npcs.remove(npcAlvo);
            }
        }
    }

    private void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    private void enviarMensagemParaTodos(String[] mensagens) {
        for (Jogador jogador : this.jogadoresConectados) {
            this.enviarMensagem(jogador.getSocket(), mensagens);
        }
    }

    private int receberMensagem(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagemLida = entrada.readLine();
            return Integer.valueOf(mensagemLida);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return -1;
    }
}
