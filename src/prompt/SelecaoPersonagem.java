package prompt;

import multiplayer.Jogador;
import personagem.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SelecaoPersonagem {
    private final ArrayList<Jogador> jogadoresConectados;
    private final GerenciadorDePersonagem gerenciadorDePersonagem;
    public SelecaoPersonagem(ArrayList<Jogador> jogadoresConectados) {
        this.jogadoresConectados = jogadoresConectados;
        this.gerenciadorDePersonagem = new GerenciadorDePersonagem();
    }
    public void inicarSelecao(Jogador jogadorConectado) {
        int opcao;
        String[] mensagens = {
                "Selecione uma das opções abaixo:",
                "1 - Selecionar personagem",
                "2 - Sair do Jogo",
                "Digite aqui: ",
                "true"
        };
        do {
            this.enviarMensagem(jogadorConectado.getSocket(), mensagens);
            opcao = this.receberMensagem(jogadorConectado.getSocket());
        } while(!this.verificaOpcaoInicial(opcao, jogadorConectado));
    }

    private boolean verificaOpcaoInicial(int opcao, Jogador jogadorConectado) {
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
        this.enviarMensagemParaTodos(mensagens);
        System.out.println("[SERVIDOR] Jogo finalizado");
        System.exit(0);
    }

    public void selecionarPersonagem(Jogador jogadorConectado) {
        int opcao;
        do {
            this.mostrarOpcoesPersonagem(jogadorConectado.getSocket());
            opcao = receberMensagem(jogadorConectado.getSocket());
        } while(!this.verificaOpcaoPersonagem(opcao, jogadorConectado.getSocket()));
        criarPersonagem(opcao, jogadorConectado);
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

    private void criarPersonagem(int idClassePersonagem, Jogador jogadorConectado) {
        if(idClassePersonagem == 1) {
            jogadorConectado.setHeroi(this.gerenciadorDePersonagem.criarArqueiro());
        } else if(idClassePersonagem == 2) {
            jogadorConectado.setHeroi(this.gerenciadorDePersonagem.criarGuerreiro());
        } else if(idClassePersonagem == 3) {
            jogadorConectado.setHeroi(this.gerenciadorDePersonagem.criarMago());
        } else {
            jogadorConectado.setHeroi(this.gerenciadorDePersonagem.criarEscudeiro());
        }
        this.enviarMensagem(jogadorConectado.getSocket(), jogadorConectado.getHeroi().enviarMensagemCriacao());
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
        } catch(Exception excecao) {
            excecao.printStackTrace();
        }
        return -1;
    }
}
