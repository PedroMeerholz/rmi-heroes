import entidades.*;
import prompt.SelecaoPersonagens;

import java.util.ArrayList;

public class App {
    private static SelecaoPersonagens selecaoPersonagens = new SelecaoPersonagens();

    public static void main(String[] args) {
        // Teste

        ArrayList<Npc> npcs = new ArrayList<Npc>();
        ArrayList<Personagem> personagens = new ArrayList<Personagem>();
        System.out.println("===== RMI HEROES =====");
        if(selecaoPersonagens.mostrarOpcoesIniciais() == 2) {
            System.out.println("Jogo finalizado!");
            System.exit(0);
        } else {
            Personagem personagem = selecaoPersonagens.selecionarPersonagem();
            personagens.add(personagem);
        }
        npcs = CriadorDePersonagem.criarNpcs();
    }
}