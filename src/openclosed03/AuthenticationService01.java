package openclosed03;

public final class AuthenticationService01 {

	// Para explicar el problema add el de apple

	public void siging(AuthenticationEngine00 userEngine) {
		switch (userEngine) {
		case facebook:
			signingFacebook();
			break;
		case google:
			signingGoogle();
			break;
		case X:
			signingTwitter();
			break;
		case apple:
			signingApple();
			break;
			//modificando para añadir gmail
		case gmail:
			signingGmail();
			break;
		}
	}

	private void signingGmail() {
		// TODO Auto-generated method stub

	}

	public void signingApple() {

	}

	public void signingFacebook() {

	}

	public void signingGoogle() {

	}

	public void signingTwitter() {

	}
}
