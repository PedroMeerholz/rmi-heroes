package prompt;

import jogador.Jogador;
import personagem.GerenciadorDePersonagem;
import personagem.herois.Heroi;
import personagem.npcs.Npc;
import personagem.Personagem;
import socket.SocketIOManager;
import turno.Turno;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Npc> npcs;
    private ArrayList<Heroi> herois;
    private ArrayList<Personagem> heroisComBonusDeDefesa;
    private Turno turno = new Turno();
    private SocketIOManager socketIOManager;
    private ArrayList<Jogador> sockets;

    public Jogo(ArrayList<Jogador> sockets) {
        GerenciadorDePersonagem.criarNpcs();
        this.npcs = GerenciadorDePersonagem.npcs;
        this.heroisComBonusDeDefesa = new ArrayList<>();
        this.socketIOManager = new SocketIOManager();
        this.sockets = sockets;
    }

    public void iniciarJogo() {
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
        Scanner scanner = new Scanner(System.in);
        String mensagem = String.format(
                "Turno %d\n" +
                "Que ação você deseja fazer?" +
                "1 - Atacar" +
                "2 - Defender" +
                "Digite aqui: " , turno);
        do {
//            System.out.printf("Turno %d\n", turno);
//            System.out.println("Que ação você deseja fazer? ");
//            System.out.println("1 - Atacar");
//            System.out.println("2 - Defender");
//            System.out.printf("Digite aqui: ");
//            this.socketIOManager.enviarMensagemParaOCliente();
            opcao = scanner.nextInt();
        } while (this.verificaOpcaoDoTurnoDoJogador(opcao) == false);
    }

    private boolean verificaOpcaoDoTurnoDoJogador(int opcao) {
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            this.acionaAcaoTurnoDoJogador(opcao);
            return true;
        }
    }

    private void acionaAcaoTurnoDoJogador(int opcao) {
        if(opcao == 1) {
            // atacar
            int alvoIdx = selecionarAlvo();
            alvoIdx -= 1;
            Npc npcAlvo = npcs.get(alvoIdx);
            herois.get(0).atacar(npcAlvo);
            if(npcAlvo.getVidaAtual() <= 0) {
                npcAlvo.setVivo(false);
                if(npcAlvo.getNome() != "Necromancer") {
                    npcs.remove(npcAlvo);
                }
            }
        } else {
            // defender
            herois.get(0).defender();
            heroisComBonusDeDefesa.add(herois.get(0));
        }
    }

    private int selecionarAlvo() {
        int alvo;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Selecione seu alvo abaixo");
            for(int i = 0; i < npcs.size(); i++){
                if(i == 0) {
                    System.out.println("1 - Necromancer");
                } else {
                    System.out.printf("%d - %d° Servo\n", i+1, i);
                }
            }
            System.out.print("Digite aqui: ");
            alvo = scanner.nextInt();
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
            System.out.println("Você foi derrotado e o Necromancer escapou...");
            System.out.println("A humanidade sofrerá por um longo tempo!");
            System.out.println("==== FIM DE JOGO ====");
            System.exit(0);
        }
    }

    private void verificarNpcsVivos() {
        if (!npcs.get(0).isVivo()) {
            System.out.println("Você derrotou o Necromancer e seus servos...");
            System.out.println("A humanidade está a salvo e você foi reconhecido como herói!");
            System.out.println("==== FIM DE JOGO ====");
            System.exit(0);
        }
    }
}
