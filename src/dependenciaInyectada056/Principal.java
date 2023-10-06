package dependenciaInyectada056;

import inversionDependencia058.ADos;
import inversionDependencia058.AUno;

public class Principal {
	//Softare cliente (aunque ponga main)
public static void main(String[] args) {
	//inyeccion
	//Aqui B se va a comportar como diga Auno
	Dependiente b=new Dependiente(new Necesitada());
	//Aqui podemos cambiar su comportamiento
	
}
}
