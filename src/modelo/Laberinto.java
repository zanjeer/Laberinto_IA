package modelo;

public class Laberinto {
		private Celda[][] celdas;
	
		
		public Laberinto(Celda[][] celdas) {
			this.celdas = celdas;
		}
	
		
		
		public Celda[][] getCeldas() {
			return celdas;
		}
		
		public boolean equals(Object obj) {
		        if(!(obj instanceof Estado)) return false;
		       return((Laberinto)obj).getCeldas().equals(this.getCeldas());
		    }	
}
