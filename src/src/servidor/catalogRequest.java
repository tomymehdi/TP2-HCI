package servidor;

public class catalogRequest extends Request {

	public static final String COMMON_URL = "http://eiffel.itba.edu.ar/hci/service/Catalog.groovy?method=";
	
	public catalogRequest(String method) {
		this(method, null);
	}

	public catalogRequest(String method, String tosend) {
		
		super(COMMON_URL + method, tosend);

		
	}
	
}