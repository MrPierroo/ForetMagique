package controler;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InitEnvironnement exE = new InitEnvironnement();

		new Thread(exE).start();


	}

}
