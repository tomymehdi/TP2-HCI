package servidor;

public class OrderRequest extends Request {

	public static final String SECURE_URL = "http://eiffel.itba.edu.ar/hci/service/Order.groovy?method=";
	
	public OrderRequest(String method) {
		this(method, null);
	}

	public OrderRequest(String method, String tosend) {
		super(SECURE_URL + method, tosend);
	}
}
