package prompt;

import personagem.GerenciadorDePersonagem;
import personagem.resultadoAtaque.ResultadoAtaque;
import personagem.herois.Heroi;
import personagem.npcs.Npc;
import personagem.Personagem;
import turno.Turno;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Jogo {
    private ArrayList<Npc> npcs;
    private ArrayList<Heroi> herois;
    private ArrayList<Personagem> heroisComBonusDeDefesa;
    private Socket jogadorConectado;
    private Turno turno = new Turno();

    public Jogo(Socket jogadorConectado) {
        GerenciadorDePersonagem.criarNpcs();
        this.npcs = GerenciadorDePersonagem.npcs;
        this.heroisComBonusDeDefesa = new ArrayList<>();
        this.jogadorConectado = jogadorConectado;
    }

    private void mostrarMensagemInicial() {
        String[] mensagens = {
                "[SERVIDOR] Você adentra em um castelo antigo e se deparou um Necromancer e três de seus servos... ",
                "[SERVIDOR] Esses monstros jamais podem sair do castelo, ou a humanidade estará em apuros...",
                "[SERVIDOR] Você tem o dever de derrotá-los e evitar que eles escapem!"
        };
        this.enviarMensagem(this.jogadorConectado, mensagens);
    }

    public void iniciarJogo() {
        this.mostrarMensagemInicial();
        SelecaoPersonagem selecaoPersonagem = new SelecaoPersonagem(this.jogadorConectado);
        selecaoPersonagem.inicarSelecao();
        this.herois = GerenciadorDePersonagem.herois;
        do {
            this.executarTurnoDoJogador(turno.getTurno());
            this.executarTurnoDosNpcs();
            this.removerBonusDeDefesaExistente();
            this.verificarPersonagensVivos();
            turno.avancarTurno();
        } while (!npcs.isEmpty() || !herois.isEmpty());
    }

    private void executarTurnoDoJogador(int turno) {
        int opcao;
        String[] mensagens = {
                String.format("Turno %d\n", turno),
                "Que ação você deseja fazer? ",
                "1 - Atacar",
                "2 - Defender",
                "Digite aqui: ",
                "true"
        };
        do {
            this.enviarMensagem(this.jogadorConectado, mensagens);
            opcao = this.receberMensagem(this.jogadorConectado);
        } while (this.verificaOpcaoDoTurnoDoJogador(opcao) == false);
    }

    private boolean verificaOpcaoDoTurnoDoJogador(int opcao) {
        System.out.println("Verificação de opção");
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            this.acionaAcaoTurnoDoJogador(opcao);
            return true;
        }
    }

    private void acionaAcaoTurnoDoJogador(int opcao) {
        System.out.printf("Opção: %d\n", opcao);
        if(opcao == 1) {
            // atacar
            int alvoIdx = selecionarAlvo();
            alvoIdx -= 1;
            Npc npcAlvo = npcs.get(alvoIdx);
            ResultadoAtaque resultadoAtaque = herois.get(0).atacar(npcAlvo);
            verificarResultadoAtaque(resultadoAtaque);
            verificarVidaNpcAlvo(npcAlvo);
        } else {
            // defender
            herois.get(0).defender();
            heroisComBonusDeDefesa.add(herois.get(0));
        }
    }

    private void verificarResultadoAtaque(ResultadoAtaque resultadoAtaque) {
        ArrayList<String> listaMensagens = new ArrayList<>();
        if(resultadoAtaque.getDano() == 0) {
            listaMensagens.add("O ataque não causou nenhum dano ao alvo.");
        } else {
            listaMensagens.add(String.format("Dano causado: %d\n", resultadoAtaque.getDano()));
            listaMensagens.add(String.format("Vida atual do alvo: %d\n\n", resultadoAtaque.getVidaAtualAlvo()));
        }
        String[] mensagens = listaMensagens.toArray(new String[0]);
        this.enviarMensagem(this.jogadorConectado, mensagens);
    }

    private int selecionarAlvo() {
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
            this.enviarMensagem(this.jogadorConectado, mensagensParaEnvio);
            alvo = this.receberMensagem(this.jogadorConectado);
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
            this.atacarHeroiAlvo(this.npcs.get(i), this.herois.get(alvo));
        }
    }

    private void atacarHeroiAlvo(Npc npc, Heroi heroi) {
        System.out.printf("%s está atacando...\n", npc.getNome());
        System.out.printf("%s atacou o %s. ", npc.getNome(), heroi.classeHeroi());
        npc.atacar(heroi);
        if(heroi.getVidaAtual() <= 0) {
            heroi.setVivo(false);
            herois.remove(heroi);
        }
    }

    private void verificarPersonagensVivos() {
        verificarHeroisVivos();
        verificarNpcsVivos();
    }

    private void verificarHeroisVivos() {
        if (herois.size() < 1) {
            String[] mensagens = {
                    "Você foi derrotado e o Necromancer escapou...",
                    "A humanidade sofrerá por um longo tempo!",
                    "==== FIM DE JOGO ====",
                    "exit"
            };
            this.enviarMensagem(this.jogadorConectado, mensagens);
            System.exit(0);
        }
    }

    private void verificarNpcsVivos() {
        if (!npcs.get(0).isVivo()) {
            String[] mensagens = {
                    "Você derrotou o necromancer e seus servos...",
                    "A humanidade está a salvo e você foi reconhecido como herói!",
                    "==== FIM DE JOGO ====",
                    "exit"
            };
            this.enviarMensagem(this.jogadorConectado,  mensagens);
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

    private int receberMensagem(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagemLida = entrada.readLine();
            System.out.println(mensagemLida);
            return Integer.valueOf(mensagemLida);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return -1;
    }
}
