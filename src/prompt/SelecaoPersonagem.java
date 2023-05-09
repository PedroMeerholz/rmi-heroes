package prompt;

import personagem.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SelecaoPersonagem {
    private final Socket jogadorConectado;
    public SelecaoPersonagem(Socket jogadorConectado) {
        this.jogadorConectado = jogadorConectado;
    }
    public int inicarSelecao() {
        int opcao;
        do {
            String[] mensagens = {
                    "[SERVIDOR] Selecione uma das opções abaixo:",
                    "[SERVIDOR] 1 - Selecionar personagem",
                    "[SERVIDOR] 2 - Sair do Jogo",
                    "[SERVIDOR] Digite aqui: ",
                    "true"
            };
            this.enviarMensagem(this.jogadorConectado, mensagens);
            opcao = this.receberMensagem(this.jogadorConectado);
            // Até aqui chega
        } while(!this.verificaOpcaoInicial(opcao));
        return -2;
    }

    private boolean verificaOpcaoInicial(int opcao) {
        System.out.println("Verificando opção...");
        if(opcao < 1 || opcao > 2) {
            return false;
        } else {
            selecionarPersonagem();
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
        } while(!this.verificaOpcaoPersonagem(opcao));
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

    public void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
    }

    public int receberMensagem(Socket socket) {
        try {
            System.out.println("Recebendo mensagem...");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagemLida = entrada.readLine();
            System.out.println(mensagemLida);
            return Integer.valueOf(mensagemLida);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
        return -1;
    }
}
