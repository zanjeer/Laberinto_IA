package laberinto;

import java.util.ArrayList;
import java.util.PriorityQueue;
import modelo.Celda;
import modelo.Estado;
import modelo.Laberinto;

public class Busqueda {

    private PriorityQueue<Estado> abiertos;
    private ArrayList<Estado> cerrados;
    private Estado actual;
    private Estado meta;
    private Laberinto lab;
    private Celda[][] c;
    private final char[][] l = {{'#', '#', '#', '#', '#'},
                                {' ', ' ', '#', ' ', ' '},
                                {'#', ' ', ' ', ' ', '#'},
                                {'#', ' ', '#', ' ', '#'},
                                {'#', ' ', ' ', ' ', '#'},
                                {'#', ' ', ' ', ' ', '#'},
                                {'#', '#', '#', '#', '#'}};

    public Busqueda(Estado actual, Estado meta) {
        this.abiertos = new PriorityQueue<>();
        this.cerrados = new ArrayList<>();
        this.actual = actual;
        this.meta = meta;
        mapa();
    }

    public void resultado() {
        char[][] dibujoLaberinto;
        ArrayList<Estado> resultado = buscar();

        if (resultado.isEmpty()) {
            System.out.println("sin resultado");
        };

        dibujoLaberinto = getMapaDibujado();

        for (Estado e : resultado) {
            int x = e.getCel().getX();
            int y = e.getCel().getY();

            dibujoLaberinto[x][y] = '*';
            System.out.println("\n" + e.getMovimiento() + "\n");
            for (int i = 0; i < dibujoLaberinto.length; i++) {
                for (int j = 0; j < dibujoLaberinto[0].length; j++) {
                    System.out.print(" " + dibujoLaberinto[i][j]);
                }
                System.out.println("");
            }

            dibujoLaberinto[x][y] = ' ';
        }

    }

    private ArrayList<Estado> buscar() {
        ArrayList<Estado> puntos;
        boolean win = false;
//        abiertos = null;
        abiertos.add(actual);

        while (!win && !abiertos.isEmpty()) {
            actual = abiertos.poll();
            if (!cerrados.contains(actual)) {
                cerrados.add(actual);
                expandir(actual);
                if (actual.equals(meta)) {
                    win = true;
                }

            }

            //abiertos.remove(0);
        }

        puntos = new ArrayList<>();
        if (!win) {
            return puntos;
        }

        while (actual != null) {

            puntos.add(0, actual);
            actual = actual.getAntecesor();

        }
        return puntos;
    }

    private void expandir(Estado actual) {
        expandirArriba(actual);
        expandirAbajo(actual);
        expandirDerecha(actual);
        expandirIzquerda(actual);
    }

    private void expandirArriba(Estado actual) {
        int x = actual.getCel().getX();
        int y = actual.getCel().getY();
        int nx = x - 1;
        Celda nuevo;
        Estado ex1;
        String mov1;

        if (actual.getCel().getX() - 1 >= 0) {
            if (lab.getCeldas()[x - 1][y].getTipo().equals(new String("C"))) {
                nx = x - 1;
                mov1 = " moverse Arriba [" + nx + "] [" + y + "]";
                nuevo = new Celda(x - 1, y, new String("C"));
                ex1 = new Estado(nuevo, actual, mov1);
                ex1.calcularF(meta, actual);
                abiertos.offer(ex1);
            }
        }
    }

    private void expandirAbajo(Estado actual) {
        int x = actual.getCel().getX();
        int y = actual.getCel().getY();
        int nx = x + 1;
        Celda nuevo;
        Estado ex1;
        String mov1;

        if (actual.getCel().getX() + 1 <= 6) {
            if (lab.getCeldas()[x + 1][y].getTipo().equals(new String("C"))) {
                mov1 = " moverse Abajo [" + nx + "] [" + y + "]";
                nuevo = new Celda(x + 1, y, new String("C"));
                ex1 = new Estado(nuevo, actual, mov1);
                ex1.calcularF(meta, actual);
                abiertos.offer(ex1);
            }
        }
    }

    private void expandirDerecha(Estado actual) {
        int x = actual.getCel().getX();
        int y = actual.getCel().getY();
        int ny = y + 1;
        Celda nuevo;
        Estado ex1;
        String mov1;

        if (y + 1 <= 4) {
            if (lab.getCeldas()[x][y + 1].getTipo().equals(new String("C"))) {
                mov1 = " moverse a la Derecha [" + x + "] [" + ny + "]";
                nuevo = new Celda(x, y + 1, new String("C"));
                ex1 = new Estado(nuevo, actual, mov1);
                ex1.calcularF(meta, actual);
                abiertos.offer(ex1);
            }
        }
    }

    private void expandirIzquerda(Estado actual) {
        int y = actual.getCel().getY();
        int x = actual.getCel().getX();
        int ny = y - 1;
        Estado ex1;
        String mov1;
        Celda nuevo;

        if (y - 1 >= 0) {
            if (lab.getCeldas()[x][y - 1].getTipo().equals("C")) {
                mov1 = " moverse a la Izquerda [" + x + "] [" + ny + "]";
                nuevo = new Celda(x, y - 1, "C");
                ex1 = new Estado(nuevo, actual, mov1);
                ex1.calcularF(meta, actual);
                abiertos.offer(ex1);
            }
        }
    }

    private char[][] getMapaDibujado() {
        return l;
    }

    public void mapa() {
        c = new Celda[7][5];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (l[i][j] == '#') {
                    c[i][j] = new Celda(i, j, "P");
                } else if (l[i][j] == ' ') {
                    c[i][j] = new Celda(i, j, "C");
                }
            }
        }
        lab = new Laberinto(c);
    }

}
