import personagem.GerenciadorDePersonagem;
import personagem.classesPai.Npc;
import personagem.classesPai.Personagem;
import prompt.Jogo;
import prompt.SelecaoPersonagem;
import utils.Turno;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Teste
        SelecaoPersonagem selecaoPersonagem = new SelecaoPersonagem();
        GerenciadorDePersonagem.criarNpcs();
        ArrayList<Npc> npcs = GerenciadorDePersonagem.npcs;
        ArrayList<Personagem> herois = GerenciadorDePersonagem.herois;
        Jogo jogo = new Jogo();

        System.out.println("===== RMI HEROES =====");

        if(selecaoPersonagem.mostrarOpcoesIniciais() == 2) {
            System.out.println("Jogo finalizado!");
            System.exit(0);
        } else {
            selecaoPersonagem.selecionarPersonagem();
        }
        System.out.printf("Qtd personagens: %d\n", herois.size());

        System.out.println("Você adentra em um castelo antigo e se acordou um Necromancer e três de seus servos...");
        System.out.println("Esses monstros jamais podem sair do castelo, ou a humanidade estará em apuros...");
        System.out.println("Você tem o dever de derrotá-los e evitar que eles escapem!");

        Turno turno = new Turno();
        while(npcs.get(0).isVivo() == true || herois.size() > 0) {
            // Turno personagem
            jogo.removerBonusDeDefesaExistente();
            jogo.mostrarOpcoesDoTurno(turno.getTurno());
            turno.avancarTurno();
        }
    }
}