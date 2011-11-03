package errors;

import servidor.RequestError;

public class orderError implements RequestError {

	private int errNum;
	private String Language = null;

	public orderError(int errNum, String Language) {

		this.errNum = errNum;
		this.Language = Language;
	}

	public void handle(Exception e) {

		if (Language == "Spanish") {
			switch (errNum) {

			case 1:
				System.out
						.println("La solicitud requiere de un metodo el cual no fue provisto.");
			case 4:
				System.out
						.println("La solicitud requiere un nombre de usuario el cual no fue provisto.");
			case 6:
				System.out
						.println("La solicitud requiere el token de autenticacioÌ�n el cual no fue provisto.");
			case 13:
				System.out
						.println("La solicitud requiere de un identificador de orden el cual no fue provisto.");
			case 14:
				System.out
						.println("La solicitud requiere de un identificador de direccioÌ�n el cual no fue provisto.");
			case 101:
				System.out.println("El meÌ�todo suministrado es invaÌ�lido.");
			case 104:
				System.out.println("Usuario invalido.");
			case 105:
				System.out
						.println("El token de autenticacioÌ�n es invaÌ�lido.");
			case 115:
				System.out
						.println("El identificador de orden suministrado es invaÌ�lido.");
			case 116:
				System.out
						.println("La operacioÌ�n solicitada es invaÌ�lida para el estado de la orden.");
			case 117:
				System.out
						.println("El identificador de la direccioÌ�n suministrado es invaÌ�lido.");
			case 999:
				System.out
						.println("Se produjo un error inesperado procesando la solicitud.");
			default:
				System.out
						.println("Se produjo un error en el acceso a la orden.");

			}
		} else {
			switch (errNum) {
			case 1:
				System.out.println("Missing method.");
			case 4:
				System.out.println(" Missing username.");
			case 6:
				System.out.println("Missing authentication token.");
			case 13:
				System.out.println("Missing order id");
			case 14:
				System.out.println("Missing address id.");
			case 101:
				System.out.println("Invalid method.");
			case 104:
				System.out.println("Invalid user.");
			case 105:
				System.out.println("Invalid authentication token.");
			case 115:
				System.out.println("Invalid order id.");
			case 116:
				System.out.println("Invalid operation.");
			case 117:
				System.out.println("Invalid address id.");
			case 999:
				System.out.println("Unknown error");
			default:
				System.out.println("Error accesing to order.");
			}
		}
	}

}
