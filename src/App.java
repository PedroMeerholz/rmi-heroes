import entidades.*;
import prompt.SelecaoPersonagens;

import java.util.ArrayList;

public class App {
    private static SelecaoPersonagens selecaoPersonagens = new SelecaoPersonagens();
    private static ArrayList<Personagem> personagens = new ArrayList<Personagem>();
    public static void main(String[] args) {
        // Teste
        System.out.println("===== RMI HEROES =====");
        if(selecaoPersonagens.mostrarOpcoesIniciais() == 2) {
            System.out.println("Jogo finalizado!");
            System.exit(0);
        } else {
            int idClassePersonagem = selecaoPersonagens.selecionarPersonagem();
            if(idClassePersonagem == 1) {
                Arqueiro arqueiro = new Arqueiro(59, 640, 26);
                personagens.add(arqueiro);
                System.out.println(arqueiro.toString());
            } else if(idClassePersonagem == 2) {
                Guerreiro guerreiro = new Guerreiro(64, 652, 39);
                personagens.add(guerreiro);
                System.out.println(guerreiro.toString());
            } else if(idClassePersonagem == 3) {
                Mago mago = new Mago(53, 590, 21);
                personagens.add(mago);
                System.out.println(mago.toString());
            } else if(idClassePersonagem == 4) {
                Suporte suporte = new Suporte(47, 595, 29);
                personagens.add(suporte);
                System.out.println(suporte.toString());
            }
        }
    }
}