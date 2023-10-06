package dependenciaCreada055;

import inversionDependencia058.AInterface;

public class Dependiente {
	//DEpendencia creada
	Necesitada necesitada=new Necesitada();

	public Dependiente() {
		super();
	}

	public void setA(Necesitada a) {
		this.necesitada = a;
	}
	
}
