package inversioncontrol06;

public class MIPrograma {
	//inversion de control
	public void ejecutar(Hooks hook) {
		hook.onArranca();
		// cosas propias
//		System.out.println("Hago cosas nazis");
		hook.onTerminar();
	}

	public void ejecutar() {
		ejecutar(new Hooks() {
		});
		
	}
}
