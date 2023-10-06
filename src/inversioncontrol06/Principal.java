package inversioncontrol06;

public class Principal {

	public static void main(String[] args) {
		MIPrograma miPrograma = new MIPrograma();
		Hooks MyHooks = new Hooks() {
			@Override
			public void onArranca() {
				System.out.println("soy una implementacion concreta de arraque");
			}

			@Override
			public void onTerminar() {
				System.out.println("soy una implementacion de finalizacion");
			}
		};
//		inversion de dependencias
		miPrograma.ejecutar(MyHooks);
//		miPrograma.ejecutar();
	}
}
