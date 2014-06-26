package modelo;

public class Estado implements Comparable<Estado> {

    private Celda cel;
    private Estado antecesor;
    private final String movimiento;
    double f, h, g;

    public Estado(Celda cel, Estado antecesor, String movimiento) {

        this.cel = cel;
        this.antecesor = antecesor;
        this.movimiento = movimiento;
        f = 0.0;
        h = 0.0;
        g = 0.0;
    }

    public void calcularF(Estado actualMeta, Estado estadoMeta) {
        //Celda $this = (Celda) data;
        Celda $this = cel;
        Celda meta = estadoMeta.getCel();
        Celda actual = actualMeta.getCel();

        g = actualMeta.getG()
                + Math.sqrt(((Math.pow(Math.abs(actual.getX() - $this.getX()), 2))
                        + (Math.pow(Math.abs(actual.getY() - $this.getY()), 2))));

        h = Math.sqrt(((Math.pow(Math.abs(meta.getX() - $this.getX()), 2))
                + (Math.pow(Math.abs(meta.getY() - $this.getY()), 2))));

        f = g + h;
    }

    public Celda getCel() {
        return cel;
    }

    public Estado getAntecesor() {
        return antecesor;
    }

    public String getMovimiento() {
        return movimiento;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Estado)) {
            return false;
        }
        return cel.equals(((Estado) obj).getCel());
    }

    @Override
    public String toString() {
        return this.movimiento;
    }

    public double getF() {
        return f;
    }

    public double getH() {
        return h;
    }

    public double getG() {
        return g;
    }

    @Override
    public int compareTo(Estado o) {
        if (o.getF() == this.getF()) {
            return 0;
        } else if (this.getF() > o.getF()) {
            return 1;
        }

        return -1;
    }

}
