package inversioncontrol06;

public interface Hooks {
	public default void onArranca() {
		System.out.println("soy el programa");
	};
	public default void onTerminar() {
		System.out.println("termino el programa");
	};
	
}
