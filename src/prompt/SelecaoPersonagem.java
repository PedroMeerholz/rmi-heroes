package prompt;

import personagem.*;

import java.util.Scanner;

public class SelecaoPersonagem {
    public int mostrarOpcoesIniciais() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("Selecione uma das opções abaixo:");
            System.out.println("1 - Selecionar personagem");
            System.out.println("2 - Sair do Jogo");
            System.out.print("Digite aqui: ");
            opcao = scanner.nextInt();
        } while(this.verificaOpcaoInicial(opcao) == false);
        return opcao;
    }

    private boolean verificaOpcaoInicial(int opcao) {
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            return true;
        }
    }

    public void selecionarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            this.mostrarOpcoesPersonagem();
            System.out.print("Digite aqui: ");
            opcao = scanner.nextInt();
        } while(this.verificaOpcaoPersonagem(opcao) == false);
        criaPersonagem(opcao);
    }

    private boolean verificaOpcaoPersonagem(int opcao) {
        if (opcao < 1 || opcao > 4) {
            System.out.println("\nSelecione uma opção válida...");
            return false;
        } else {
            return true;
        }
    }

    private void criaPersonagem(int idClassePersonagem) {
        if(idClassePersonagem == 1) {
            GerenciadorDePersonagem.criarArqueiro();
        } else if(idClassePersonagem == 2) {
            GerenciadorDePersonagem.criarGuerreiro();
        } else if(idClassePersonagem == 3) {
            GerenciadorDePersonagem.criarMago();
        } else {
            GerenciadorDePersonagem.criarSuporte();
        }
    }

    private void mostrarOpcoesPersonagem() {
        System.out.println("\nSelecione sua classe de personagem abaixo:");
        System.out.println("1 - Arqueiro");
        System.out.println("2 - Guerreiro");
        System.out.println("3 - Mago");
        System.out.println("4 - Suporte");
    }
}
