package dependenciaInyectada057;

import inversionDependencia058.ADos;
import inversionDependencia058.AUno;

public class Principal {
	// Softare cliente (aunque ponga main)
	public static void main(String[] args) {
		// inyeccion
		// Compartir el objeto necesitado
		Necesitada necesitada = new Necesitada();
		Dependiente b = new Dependiente(necesitada);
		Dependiente b1 = new Dependiente(necesitada);

	}
}
