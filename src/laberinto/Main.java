package laberinto;

import modelo.Celda;
import modelo.Estado;
import modelo.Laberinto;

public class Main {

    public static void main(String[] args) {
        Estado inicio, meta;
        Celda cel_inicial, cel_fin;
        Busqueda b;

        cel_inicial = new Celda(1, 0, "C");
        cel_fin = new Celda(5, 3, "C");

        inicio = new Estado(cel_inicial, null, "Inicio");
        meta = new Estado(cel_fin, null, null);

        b = new Busqueda(inicio, meta);
        b.resultado();

    }

}
