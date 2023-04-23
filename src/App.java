import personagem.GerenciadorDePersonagem;
import personagem.herois.Heroi;
import personagem.npcs.Npc;
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
        ArrayList<Heroi> herois = GerenciadorDePersonagem.herois;
        Jogo jogo = new Jogo();

        System.out.println("===== RMI HEROES =====");

        if(selecaoPersonagem.mostrarOpcoesIniciais() == 2) {
            System.out.println("Jogo finalizado!");
            System.exit(0);
        } else {
            selecaoPersonagem.selecionarPersonagem();
        }

        System.out.println("Você adentra em um castelo antigo e se acordou um Necromancer e três de seus servos...");
        System.out.println("Esses monstros jamais podem sair do castelo, ou a humanidade estará em apuros...");
        System.out.println("Você tem o dever de derrotá-los e evitar que eles escapem!\n");

        Turno turno = new Turno();
        while(!npcs.isEmpty() || herois.size() > 0) {
            // Turno personagem
            jogo.executarTurnoDoJogador(turno.getTurno());
            jogo.executarTurnoDosNpcs();
            jogo.removerBonusDeDefesaExistente();
            turno.avancarTurno();
        }
    }
}