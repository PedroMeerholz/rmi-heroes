import entidades.Necromancer;
import entidades.Personagem;
import entidades.Servo;
import entidades.Suporte;

public class App {
    public static void main(String[] args) {
        // Teste
        Personagem suporte = new Suporte(20, 100, 10);
        System.out.println(suporte.toString());

        Personagem necromancer = new Necromancer(30, 300, 20);
        System.out.println(necromancer.toString());
        System.out.println(necromancer.atacar(suporte));
        System.out.printf("Vida do suporte após ataque: %d\n", suporte.getVidaAtual());

        Personagem servo = new Servo(12, 40, 8);
        System.out.println(servo.toString());
        System.out.println(servo.atacar(suporte));
        System.out.printf("Vida do suporte após ataque: %d\n", suporte.getVidaAtual());
    }
}