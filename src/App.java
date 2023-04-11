import entidades.*;
import prompt.SelecaoPersonagens;

import java.util.ArrayList;

public class App {
    private static SelecaoPersonagens selecaoPersonagens = new SelecaoPersonagens();

    public static void main(String[] args) {
        // Teste
        ArrayList<Personagem> personagens = new ArrayList<Personagem>();
        ArrayList<Npc> npcs = new ArrayList<Npc>();

        System.out.println("===== RMI HEROES =====");
        if(selecaoPersonagens.mostrarOpcoesIniciais() == 2) {
            System.out.println("Jogo finalizado!");
            System.exit(0);
        } else {
            int idClassePersonagem = selecaoPersonagens.selecionarPersonagem();
            if(idClassePersonagem == 1) {
                Arqueiro arqueiro = CriadorDePersonagem.criarArqueiro();
                personagens.add(arqueiro);
                System.out.println(arqueiro.toString());
            } else if(idClassePersonagem == 2) {
                Guerreiro guerreiro = CriadorDePersonagem.criarGuerreiro();
                personagens.add(guerreiro);
                System.out.println(guerreiro.toString());
            } else if(idClassePersonagem == 3) {
                Mago mago = CriadorDePersonagem.criarMago();
                personagens.add(mago);
                System.out.println(mago.toString());
            } else if(idClassePersonagem == 4) {
                Suporte suporte = CriadorDePersonagem.criarSuporte();
                personagens.add(suporte);
                System.out.println(suporte.toString());
            }
        }
        npcs = CriadorDePersonagem.criarNpcs();
    }
}