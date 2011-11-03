package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.ar.art.Debug;
import com.ar.art.ShopActivity;

public class Request {

		public String response;
		private static RequestError defaulterr;
	    private URLConnection connection;
	    private String url;
	    private String extras;
	    private String send;

		
		static {
			defaulterr =
			new RequestError() {
				public void handle(Exception e) {
					System.out.println("Error in request.");
					e.printStackTrace();
				}
			};
		}
	    
	
	    
	    /* Constructors: */
	    public Request(String method) {
	        this(method, null);
	    }
	    
	    public Request(String url, String extras) {
	        
			this.url = url;
	        
	        if (extras != null)
	            this.extras = new String(extras);
	        
	    }
	    
	    /* Getters */
	    public String getUrl() {
	        return this.url;
	    }
	    
	    public URLConnection getConnection() {
	        return this.connection;
	    }
	    
	    /* Other methods: */
	   
	    public void make(RequestResponse rr) {
	    	make(rr, null);
	    }
	    
	    public void make(RequestResponse rr, RequestError ec) {
	    	domake(rr, ec);
	    }
	    
	   /* public void makeSync(RequestResponse rr) {
	    	make(rr, null, false);
	    }
	    
	    public void makeSync(RequestResponse rr, RequestError ec) {
	    	make(rr, ec, false);
	    }*/
	    
	/*  public void make(final RequestResponse rc, final RequestError ec, boolean async) {
	    
	    	if (async) {
		    	thread = new Thread (
		    		new Runnable() {
		    			public void run() {
		    				domake(rc, ec);
		    			}
		    		}
		    	);
		    	
		    	thread.start();
		    	
	    	} else
	    		domake(rc, ec);
	    	
	    }*/
	    
	   
	   private void domake(RequestResponse rr, RequestError ec) {
	    
			if (ec == null)
				ec = defaulterr;
	    	
	    	URL currUrl;
			
			try {
				currUrl = new URL(url + "&" + extras);
			} catch (MalformedURLException e) {
				ec.handle(e);
				return;
			}
	    	try {
		        connection = currUrl.openConnection();
		        connection.setDoOutput(true);
		        
		        if (send != null) {
		            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		            writer.write(send);
		            writer.close();
		        }
		        
		        
		  BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
		        
		 String line;
		 StringBuffer buffer = new StringBuffer();
		
		 while ((line = reader.readLine()) != null) {
		        	buffer.append(line);
		        	buffer.append("\n");
		  }
		       
		 reader.close();

		 this.response = buffer.toString();
		        
		 
		 /*
		 rr.handle(DocumentBuilderFactory.newInstance()
		        		  .newDocumentBuilder()
		        		  .parse(new ByteArrayInputStream(this.response.getBytes("UTF-8")))
		 );
		 */     
	    
	    	} catch (Exception e) {
	    		ec.handle(e);
	    		return;
	    	}
	        
	    }
	}
	

