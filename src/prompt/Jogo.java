package prompt;

import personagem.GerenciadorDePersonagem;
import personagem.classesPai.Npc;
import personagem.classesPai.Personagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Npc> npcs = GerenciadorDePersonagem.npcs;
    private ArrayList<Personagem> herois = GerenciadorDePersonagem.herois;

    public void mostrarOpcoesDoTurno(int turno) {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Turno %d\n", turno);
            System.out.println("Que ação você deseja fazer? ");
            System.out.println("1 - Atacar");
            System.out.println("2 - Defender");
            System.out.printf("Digite aqui: ");
            opcao = scanner.nextInt();
        } while (this.verificaOpcaoDoTurno(opcao) == false);
    }

    private boolean verificaOpcaoDoTurno(int opcao) {
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            this.acionaAcaoTurno(opcao);
            return true;
        }
    }

    private void acionaAcaoTurno(int opcao) {
        if(opcao == 1) {
            // atacar
            int alvo = selecionarAlvo();
            herois.get(0).atacar(npcs.get(alvo-1));
        } else {
            // defender
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
}
