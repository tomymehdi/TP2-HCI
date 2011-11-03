package errors;

import servidor.RequestError;

public class catalogError implements RequestError{

	private int errNum;
	private String Language;
	
	public catalogError(int errNum, String Language) {
		this.errNum=errNum;
		this.Language=Language;
	}
	
	
	public void handle(Exception e) {
		
		if(Language=="Spanish"){
		switch(errNum){
			case 1: System.out.println("La solicitud requiere de un mÃ©todo el cual no fue provisto");
			case 2: System.out.println("La solicitud requiere de un identificador de lenguaje el cual no fue provisto.");
			case 9: System.out.println("La solicitud requiere de un identificador de categoriÌ�a el cual no fue provisto.");
			case 10: System.out.println("La solicitud requiere de un identificador de sub-categoriÌ�a el cual no fue provisto.");
			case 11: System.out.println("La solicitud requiere de un criterio de buÌ�squeda el cual no fue provisto.");
			case 101: System.out.println("El meÌ�todo suministrado es invaÌ�lido.");
			case 102: System.out.println("El identificador de lenguaje suministrado es invalido.");
			case 112: System.out.println("El identificador de categoriÌ�a suministrado es invaÌ�lido.");
			case 113:System.out.println("El identificador de sub-categoriÌ�a suministrado es invaÌ�lido.");
			case 114: System.out.println("El identificador de producto suministrado es invaÌ�lido.");
			case 999: System.out.println("Se produjo un error inesperado procesando la solicitud.");
			default: System.out.println("Se produjo un error en el acceso al catalogo");
			
		}
		}
		else{
			switch(errNum){
			case 1: System.out.println("Missing method");
			case 2: System.out.println("Missing language id");
			case 9: System.out.println("Missing category id");
			case 10: System.out.println("Missing sub-category id");
			case 11: System.out.println("Missing critery function.");
			case 101: System.out.println("Invalid method.");
			case 102: System.out.println("Invalid language id.");
			case 112: System.out.println("Invalid category id");
			case 113:System.out.println("Invalid sub-category id.");
			case 114: System.out.println("Invalid product id.");
			case 999: System.out.println("Unknown error");
			default: System.out.println("Error accesing to catalog");
			}
		}
	}

}
