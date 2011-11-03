package servidor;

public class commonRequest extends Request{

	public static final String COMMON_URL = "http://eiffel.itba.edu.ar/hci/service/Common.groovy?method=";

	
	public commonRequest(String method) {
		this(method, null);

	}

	public commonRequest(String method, String tosend) {
		super(COMMON_URL + method, tosend);
	}

	

}
