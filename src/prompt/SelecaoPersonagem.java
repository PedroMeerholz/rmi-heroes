package prompt;

import personagem.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SelecaoPersonagem {
    private final ArrayList<Socket> jogadoresConectados;
    private final GerenciadorDePersonagem gerenciadorDePersonagem;
    public SelecaoPersonagem(ArrayList jogadoresConectados) {
        this.jogadoresConectados = jogadoresConectados;
        this.gerenciadorDePersonagem = new GerenciadorDePersonagem();
    }
    public void inicarSelecao(Socket jogadorConectado) {
        int opcao;
        String[] mensagens = {
                "Selecione uma das opções abaixo:",
                "1 - Selecionar personagem",
                "2 - Sair do Jogo",
                "Digite aqui: ",
                "true"
        };
        do {
            this.enviarMensagem(jogadorConectado, mensagens);
            opcao = this.receberMensagem(jogadorConectado);
        } while(!this.verificaOpcaoInicial(opcao, jogadorConectado));
    }

    private boolean verificaOpcaoInicial(int opcao, Socket jogadorConectado) {
        if(opcao < 1 || opcao > 2) {
            return false;
        } else if (opcao == 2) {
            this.finalizarJogo();
            return false;
        } else {
            selecionarPersonagem(jogadorConectado);
            return true;
        }
    }

    private void finalizarJogo() {
        String[] mensagens = {"[SERVIDOR] Jogo finalizado", "exit"};
        for (Socket socketJogador : this.jogadoresConectados) {
            this.enviarMensagem(socketJogador, mensagens);
        }
        System.out.println("[SERVIDOR] Jogo finalizado");
        System.exit(0);
    }

    public void selecionarPersonagem(Socket jogadorConectado) {
        int opcao;
        do {
            this.mostrarOpcoesPersonagem(jogadorConectado);
            opcao = receberMensagem(jogadorConectado);
        } while(!this.verificaOpcaoPersonagem(opcao, jogadorConectado));
        criaPersonagem(opcao, jogadorConectado);
    }

    private boolean verificaOpcaoPersonagem(int opcao, Socket jogadorconectado) {
        if (opcao < 1 || opcao > 4) {
            String[] mensagens = {"\nSelecione uma opção válida..."};
            this.enviarMensagem(jogadorconectado, mensagens);
            return false;
        } else {
            return true;
        }
    }

    private void criaPersonagem(int idClassePersonagem, Socket jogadorConectado) {
        if(idClassePersonagem == 1) {
            this.gerenciadorDePersonagem.criarArqueiro(jogadorConectado);
        } else if(idClassePersonagem == 2) {
            this.gerenciadorDePersonagem.criarGuerreiro(jogadorConectado);
        } else if(idClassePersonagem == 3) {
            this.gerenciadorDePersonagem.criarMago(jogadorConectado);
        } else {
            this.gerenciadorDePersonagem.criarEscudeiro(jogadorConectado);
        }
    }

    private void mostrarOpcoesPersonagem(Socket jogadorConectado) {
        String[] mensagens = {
            "\nSelecione sua classe de personagem abaixo:",
            "1 - Arqueiro",
            "2 - Guerreiro",
            "3 - Mago",
            "4 - Escudeiro",
            "Digite aqui: ",
            "true"
        };
        this.enviarMensagem(jogadorConectado, mensagens);
    }

    private void enviarMensagem(Socket socket, String[] mensagens) {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(mensagens);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
    }

    private int receberMensagem(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagemLida = entrada.readLine();
            return Integer.valueOf(mensagemLida);
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
        return -1;
    }
}
