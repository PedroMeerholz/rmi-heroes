package prompt;

import personagem.GerenciadorDePersonagem;
import personagem.herois.Heroi;
import personagem.npcs.Npc;
import personagem.Personagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Npc> npcs = GerenciadorDePersonagem.npcs;
    private ArrayList<Heroi> herois = GerenciadorDePersonagem.herois;
    private ArrayList<Personagem> heroisComBonusDeDefesa = new ArrayList<>();

    public void executarTurnoDoJogador(int turno) {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Turno %d\n", turno);
            System.out.println("Que ação você deseja fazer? ");
            System.out.println("1 - Atacar");
            System.out.println("2 - Defender");
            System.out.printf("Digite aqui: ");
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
            int alvo = selecionarAlvo();
            herois.get(0).atacar(npcs.get(alvo-1));
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
            System.out.println("1 - Necromancer");
            System.out.println("2 - 1° Servo");
            System.out.println("3 - 2° Servo");
            System.out.println("4 - 3° Servo");
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

    public void removerBonusDeDefesaExistente() {
        if(heroisComBonusDeDefesa.size() > 0) {
            for (int i = 0; i < heroisComBonusDeDefesa.size(); i++) {
                heroisComBonusDeDefesa.get(i).removerBonusDefesa();
            }
        }
        heroisComBonusDeDefesa.clear();
    }

    public void executarTurnoDosNpcs() {
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
    }
}
