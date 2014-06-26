package modelo;

public class Celda {

    private int x, y;
    private String tipo;

    public Celda(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String t) {
        this.tipo = t;
    }

    @Override
    public String toString() {
        return " [ " + this.getX() + "," + this.getY() + "]";

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Celda)) {
            return false;
        }
        return ((Celda) obj).getX() == this.getX()
                && ((Celda) obj).getY() == this.getY();
    }

}
