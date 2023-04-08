import entidades.Personagem;
import entidades.Suporte;

public class App {
    public static void main(String[] args) {
        // Teste
        Personagem suporte = new Suporte(15, 100, 10);
        Personagem suporte2 = new Suporte(15, 100, 10);
        System.out.println(suporte.toString());
        System.out.println(suporte.atacar(suporte2));
        System.out.printf("Vida do suporte 2 após ataque do suporte 1: %d\n", suporte2.getVidaAtual());
        System.out.println(suporte.defender());
        System.out.printf("Defesa com incremento: %d\n", suporte.getDefesaAtual());
    }
}