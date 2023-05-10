package prompt;

import personagem.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SelecaoPersonagem {
    private final Socket jogadorConectado;
    private final GerenciadorDePersonagem gerenciadorDePersonagem;
    public SelecaoPersonagem(Socket jogadorConectado) {
        this.jogadorConectado = jogadorConectado;
        this.gerenciadorDePersonagem = new GerenciadorDePersonagem(this.jogadorConectado);
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
        } while(!this.verificaOpcaoInicial(opcao));
        return -99;
    }

    private boolean verificaOpcaoInicial(int opcao) {
        System.out.println("Verificando opção...");
        if(opcao < 1 || opcao > 2) {
            return false;
        } else if (opcao == 2) {
            String[] mensagens = {"[SERVIDOR] Jogo finalizado", "exit"};
            this.enviarMensagem(jogadorConectado, mensagens);
            System.out.println("[SERVIDOR] Jogo finalizado");
            System.exit(0);
            return false;
        } else {
            selecionarPersonagem();
            return true;
        }
    }

    public void selecionarPersonagem() {
        int opcao;
        do {
            this.mostrarOpcoesPersonagem();
            opcao = receberMensagem(jogadorConectado);
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
            this.gerenciadorDePersonagem.criarArqueiro();
        } else if(idClassePersonagem == 2) {
            this.gerenciadorDePersonagem.criarGuerreiro();
        } else if(idClassePersonagem == 3) {
            this.gerenciadorDePersonagem.criarMago();
        } else {
            this.gerenciadorDePersonagem.criarEscudeiro();
        }
    }

    private void mostrarOpcoesPersonagem() {
        String[] mensagens = {
            "\nSelecione sua classe de personagem abaixo:",
            "1 - Arqueiro",
            "2 - Guerreiro",
            "3 - Mago",
            "4 - Escudeiro",
            "Digite aqui: ",
            "true"
        };
        this.enviarMensagem(this.jogadorConectado, mensagens);
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
