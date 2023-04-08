import entidades.*;

public class App {
    public static void main(String[] args) {
        // Teste
        Suporte suporte = new Suporte(20, 100, 10);

        Mago mago = new Mago(30, 80, 20);
        System.out.println(mago.toString());

        Npc necromancer = new Necromancer(30, 300, 20);
        System.out.println(necromancer.atacar(mago));
        System.out.printf("Vida do mago após ataque: %d\n", mago.getVidaAtual());

        Npc servo = new Servo(12, 40, 8);
        System.out.println(servo.atacar(mago));
        System.out.printf("Vida do mago após ataque: %d\n", mago.getVidaAtual());

        suporte.curar(mago);

        System.out.printf("Vida do mago após o suporte curá-lo: %d\n", mago.getVidaAtual());
    }
}