package prompt;

import entidades.*;

import java.util.Scanner;

public class SelecaoPersonagens {
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

    public Personagem selecionarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            this.mostrarOpcoesPersonagem();
            System.out.print("Digite aqui: ");
            opcao = scanner.nextInt();
        } while(this.verificaOpcaoPersonagem(opcao) == false);
        return criaPersonagem(opcao);
    }

    private boolean verificaOpcaoPersonagem(int opcao) {
        if (opcao < 1 || opcao > 4) {
            System.out.println("\nSelecione uma opção válida...");
            return false;
        } else {
            return true;
        }
    }

    private Personagem criaPersonagem(int idClassePersonagem) {
        if(idClassePersonagem == 1) {
            Arqueiro arqueiro = CriadorDePersonagem.criarArqueiro();
            System.out.println(arqueiro.toString());
            return arqueiro;
        } else if(idClassePersonagem == 2) {
            Guerreiro guerreiro = CriadorDePersonagem.criarGuerreiro();
            System.out.println(guerreiro.toString());
            return guerreiro;
        } else if(idClassePersonagem == 3) {
            Mago mago = CriadorDePersonagem.criarMago();
            System.out.println(mago.toString());
            return mago;
        } else {
            Suporte suporte = CriadorDePersonagem.criarSuporte();
            System.out.println(suporte.toString());
            return suporte;
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
