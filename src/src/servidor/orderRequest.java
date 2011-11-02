package servidor;

public class orderRequest extends Request {

	public static final String SECURE_URL = "http://eiffel.itba.edu.ar/hci/service/Order.groovy?method=";
	
	public orderRequest(String method) {
		this(method, null);
	}

	public orderRequest(String method, String tosend) {
		super(SECURE_URL + method, tosend);
	}
}
