package errors;

import servidor.RequestError;

public class securityError implements RequestError{

	
		private int errNum;
		private String Language;
		
		public securityError(int errNum, String Language) {
			this.errNum=errNum;
			this.Language=Language;
		}
		
		
		public void handle(Exception e) {
			
			if(Language=="Spanish"){
			switch(errNum){
			
			case 1: System.out.println("La solicitud requiere de un metodo el cual no fue provisto");
			case 4: System.out.println("La solicitud requiere un nombre de usuario el cual no fue provisto.");
			case 5: System.out.println("La solicitud requiere la contraseña la cual no fue provista.");
			case 6: System.out.println("La solicitud requiere el token de autenticación el cual no fue provisto.");
			case 101: System.out.println("El método suministrado es inválido.");
			case 104: System.out.println("Usuario invalido");
			case 105: System.out.println("El token de autenticación es inválido.");
			case 999: System.out.println("Se produjo un error inesperado procesando la solicitud.");
			default: System.out.println("Se produjo un error en el acceso a seguridad");
			
			}
			}
			else{
				switch(errNum){
				case 1: System.out.println("Missing method");
				case 4: System.out.println(" Missing username.");
				case 5: System.out.println("Missing password");
				case 6: System.out.println("Missing authentication token.");
				case 101: System.out.println("Invalid method.");
				case 104: System.out.println("Invalid user.");
				case 105: System.out.println("Invalid authentication token");
				case 999: System.out.println("Unknown error");
				default: System.out.println("Error accesing to security");
				}
			}
		}


	}


