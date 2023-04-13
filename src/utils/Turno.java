package utils;

public class Turno {
    private int turno = 1;

    public int getTurno() {
        return turno;
    }

    public void avancarTurno() {
        this.turno += 1;
    }
}
