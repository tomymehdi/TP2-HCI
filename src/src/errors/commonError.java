package errors;

import servidor.RequestError;

public class commonError implements RequestError {

	private int errNum;
	private String Language;
	
	public commonError(int errNum, String Language) {
		this.errNum=errNum;
		this.Language=Language;
	}
	
	
	public void handle(Exception e) {
		
		if(Language=="Spanish"){
		switch(errNum){
		
		case 1: System.out.println("La solicitud requiere de un metodo el cual no fue provisto");
		case 2: System.out.println("La solicitud requiere de un identificador de lenguaje el cual no fue provisto.");
		case 3: System.out.println("La solicitud requiere de un identificador de país el cual no fue provisto.");
		case 101: System.out.println("El método suministrado es inválido.");
		case 102: System.out.println("El identificador de lenguaje suministrado es invalido.");
		case 103: System.out.println("El identificador de país suministrado es inválido.");
		case 999: System.out.println("Se produjo un error inesperado procesando la solicitud.");
		default: System.out.println("Se produjo un error en el acceso al common");
		
		}
		}
		else{
			switch(errNum){
			case 1: System.out.println("Missing method");
			case 2: System.out.println("Missing language id");
			case 3: System.out.println("Missing country id");
			case 101: System.out.println("Invalid method.");
			case 102: System.out.println("Invalid language id.");
			case 103: System.out.println("Invalid country id.");
			case 999: System.out.println("Unknown error");
			default: System.out.println("Error accesing to commons");
			}
		}
	}


}
