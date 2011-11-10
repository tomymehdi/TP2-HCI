package servidor;

import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import tpAndroid.main.Address;
import tpAndroid.main.App;
import tpAndroid.main.BookItem;
import tpAndroid.main.Category;
import tpAndroid.main.Item;
import tpAndroid.main.MovieItem;
import tpAndroid.main.Order;
import tpAndroid.main.SubCategory;
import tpAndroid.main.User;



public class Services {
	
	
	int langid = 1;
	App app;
	
	public Services(App app) {
		this.app=app;
	}

	
	//CARGA LAS CATEGORIAS CON LAS SUB CATEGORIAS
	public void loadCategories() {
		
		new CatalogRequest("GetCategoryList", "language_id=" + langid).make(new RequestResponse() {
			
		
			public void handle(String response) {
				
								
				List<Category> categories = new LinkedList<Category>();

				categories=parseCategories(response);
								
				app.setCategories(categories);
				
//				for(int i=0;i<categories.size();i++){
//					
//				      loadSubcategories(categories.get(i).getId());
//				}
		
				
			}

			private List<Category> parseCategories(String response) {
				
				List<Category> ans=new ArrayList<Category>();
				
				 try {
				        DocumentBuilderFactory dbf =
				            DocumentBuilderFactory.newInstance();
				        DocumentBuilder db = dbf.newDocumentBuilder();
				        InputSource is = new InputSource();
				        is.setCharacterStream(new StringReader(response));

				        Document doc = db.parse(is);
				        NodeList nodes = doc.getElementsByTagName("category");
				        

				        // iterate the categories
				        for (int i = 0; i < nodes.getLength(); i++) {
				           Element element = (Element) nodes.item(i);
				           
				           int id=Integer.parseInt(element.getAttribute("id"));

				           NodeList codeNode = element.getElementsByTagName("code");
				           Element line = (Element) codeNode.item(0);
				           String code=getCharacterDataFromElement(line);

				           NodeList nameNode = element.getElementsByTagName("name");
				           line = (Element) nameNode.item(0);
				           String name= getCharacterDataFromElement(line);
				           
				           ans.add(new Category(id,code,name));
				           
				        }
				    }
				    catch (Exception e) {
				        e.printStackTrace();
				    }
				 return ans;
			}
		});
	}
		
	private static String getCharacterDataFromElement(Element e) {
	   
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return null;
	  }
	
	public void loadSubcategories(final int category_id) {
		
		new CatalogRequest("GetSubcategoryList", "language_id=" + langid + "&category_id=" + category_id).make(new RequestResponse() {
			public void handle(String response) {
			
				List<SubCategory> subcategories = new LinkedList<SubCategory>();
				
				subcategories=parseSubCategories(response);
				
				
				
				for(int i=0;i<app.getCategories().size();i++)
					
					if(app.getCategories().get(i).getId()==category_id){
						
						app.getCategories().get(i).addSubCategory(subcategories);
					}
					
				}

				private List<SubCategory> parseSubCategories(String response) {
					
					List<SubCategory> ans=new ArrayList<SubCategory>();
					
					 try {
					        DocumentBuilderFactory dbf =
					            DocumentBuilderFactory.newInstance();
					        DocumentBuilder db = dbf.newDocumentBuilder();
					        InputSource is = new InputSource();
					        is.setCharacterStream(new StringReader(response));

					        Document doc = db.parse(is);
					        NodeList nodes = doc.getElementsByTagName("subcategory");
					        

					        // iterate the categories
					        for (int i = 0; i < nodes.getLength(); i++) {
					           Element element = (Element) nodes.item(i);
					           
					           int id=Integer.parseInt(element.getAttribute("id"));

					           NodeList codeNode = element.getElementsByTagName("code");
					           Element line = (Element) codeNode.item(0);
					           String code=getCharacterDataFromElement(line);

					           NodeList nameNode = element.getElementsByTagName("name");
					           line = (Element) nameNode.item(0);
					           String name= getCharacterDataFromElement(line);
					           
					           NodeList categNode = element.getElementsByTagName("category_id");
					           line = (Element) categNode.item(0);
					           int categ= Integer.parseInt(getCharacterDataFromElement(line));
					         
					           ans.add(new SubCategory(id,code,name,categ));
					           
					        }
					    }
					    catch (Exception e) {
					        e.printStackTrace();
					    }
					 return ans;
				}
			});
	}
	
	
	//SI LE PASAS 0 A SUB_CATEGORY DEVUELVE EL DE LAS CATEGORIAS
	public void loadItems(int category_id, int subcategory_id) {
		
		final RequestResponse rcb = new RequestResponse() {
			
			public void handle(String response) {
			
				List<Item> items = new LinkedList<Item>();

				items=parseItems(response);
								
				
				
				app.setItems(items);
				
				//for(int i=0; i<items.size();i++){
					
				//loadInfo(items.get(i).getId(),response);
				//}
				
			}
		

		};

		if (category_id != 0) {
			if (subcategory_id == 0) {
				new CatalogRequest("GetProductListByCategory", "language_id=" + langid + "&category_id=" + category_id).make(rcb);
			} else {
				new CatalogRequest("GetProductListBySubcategory", "language_id=" + langid + "&category_id=" + category_id + "&subcategory_id=" + subcategory_id).make(rcb);
			}
		}
	}


	public void loadInfo(Item item) {
		
		new CatalogRequest("GetProduct", "product_id=" + item.getId()).make(new RequestResponse() {
			
			Item item=null;
			
			
			public void handle(String response) {
				
				
				if(item instanceof MovieItem ){
					parseMovieInfo(response,item);
				}
				else{
					parseBookInfo(response,item);
				}
				
			}
			
		
			});
			}
	
	private void parseMovieInfo(String response, Item item){
		
		
		 try {
		        DocumentBuilderFactory dbf =
		            DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(response));

		        Document doc = db.parse(is);
		        NodeList nodes = doc.getElementsByTagName("product");
		        

		        // iterate the categories
		        for (int i = 0; i < nodes.getLength(); i++) {
		           Element element = (Element) nodes.item(i);
		           

		           NodeList aut = element.getElementsByTagName("actors");
		           Element line = (Element) aut.item(0);
		           String actors=getCharacterDataFromElement(line);

		           NodeList pub = element.getElementsByTagName("format");
		           line = (Element) pub.item(0);
		           String format= getCharacterDataFromElement(line);
		           
		           NodeList pubd = element.getElementsByTagName("release_date");
		           line = (Element) pubd.item(0);
		           Date release_date= Date.valueOf(getCharacterDataFromElement(line));
		           
		           NodeList subs = element.getElementsByTagName("subtitles");
		           line = (Element) subs.item(0);
		           String subtitles= getCharacterDataFromElement(line);
		           
		           NodeList reg = element.getElementsByTagName("region");
		           line = (Element) reg.item(0);
		           String region= getCharacterDataFromElement(line);
		           
		           
		           NodeList arat = element.getElementsByTagName("aspect_ratio");
		           line = (Element) arat.item(0);
		           String aspect_ratio= getCharacterDataFromElement(line);
		           
		           NodeList nd = element.getElementsByTagName("number_discs");
		           line = (Element) nd.item(0);
		           int number_discs= Integer.parseInt(getCharacterDataFromElement(line));
		           
		           NodeList rt = element.getElementsByTagName("run_time");
		           line = (Element) rt.item(0);
		           int run_time= Integer.parseInt(getCharacterDataFromElement(line));
		           
		           NodeList lan = element.getElementsByTagName("language");
		           line = (Element) lan.item(0);
		           String lang= getCharacterDataFromElement(line);
		           
		           NodeList asin = element.getElementsByTagName("ASIN");
		           line = (Element) asin.item(0);
		           String ASIN= getCharacterDataFromElement(line);
		           
		           ((MovieItem) item).addInfo(actors,format, lang,  subtitles, region, aspect_ratio,number_discs,  release_date,  run_time,  ASIN);
		           
		        }
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	private void parseBookInfo(String response, Item item) {
				
				
				 try {
				        DocumentBuilderFactory dbf =
				            DocumentBuilderFactory.newInstance();
				        DocumentBuilder db = dbf.newDocumentBuilder();
				        InputSource is = new InputSource();
				        is.setCharacterStream(new StringReader(response));

				        Document doc = db.parse(is);
				        NodeList nodes = doc.getElementsByTagName("product");
				        

				        // iterate the categories
				        for (int i = 0; i < nodes.getLength(); i++) {
				           Element element = (Element) nodes.item(i);
				           

				           NodeList aut = element.getElementsByTagName("authors");
				           Element line = (Element) aut.item(0);
				           String authors=getCharacterDataFromElement(line);

				           NodeList pub = element.getElementsByTagName("publisher");
				           line = (Element) pub.item(0);
				           String publisher= getCharacterDataFromElement(line);
				           
				           NodeList pubd = element.getElementsByTagName("published_date");
				           line = (Element) pubd.item(0);
				           Date pubdate= Date.valueOf(getCharacterDataFromElement(line));
				           
				           NodeList isbn10 = element.getElementsByTagName("ISBN_10");
				           line = (Element) isbn10.item(0);
				           String ISBN_10= getCharacterDataFromElement(line);
				           
				           NodeList isbn13 = element.getElementsByTagName("ISBN_13");
				           line = (Element) isbn13.item(0);
				           String ISBN_13= getCharacterDataFromElement(line);
				           
				           NodeList lan = element.getElementsByTagName("language");
				           line = (Element) lan.item(0);
				           String lang= getCharacterDataFromElement(line);
				           
				           ((BookItem) item).addInfo(authors,publisher,pubdate,ISBN_10,ISBN_13,lang);
				           
				        }
				    }
				 catch (Exception e) {
				        e.printStackTrace();
				    }
			}
		
	private List<Item> parseItems(String response) {
		
		List<Item> ans=new ArrayList<Item>();
		
		 try {
		        DocumentBuilderFactory dbf =
		            DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(response));

		        Document doc = db.parse(is);
		        NodeList nodes = doc.getElementsByTagName("product");
		        

		        // iterate the categories
		        for (int i = 0; i < nodes.getLength(); i++) {
		           Element element = (Element) nodes.item(i);
		           
		           int id=Integer.parseInt(element.getAttribute("id"));

		           NodeList sales_rank = element.getElementsByTagName("sales_rank");
		           Element line = (Element) sales_rank.item(0);
		           int sales=Integer.parseInt(getCharacterDataFromElement(line));
		           
		           NodeList sid = element.getElementsByTagName("subcategory_id");
		            line = (Element) sid.item(0);
		           int scateg=Integer.parseInt(getCharacterDataFromElement(line));
		           
		           NodeList cid = element.getElementsByTagName("category_id");
		            line = (Element) cid.item(0);
		           int categ=Integer.parseInt(getCharacterDataFromElement(line));
		           
		           NodeList nameNode = element.getElementsByTagName("name");
		           line = (Element) nameNode.item(0);
		           String name= getCharacterDataFromElement(line);
		           
		           NodeList pr = element.getElementsByTagName("price");
		            line = (Element) pr.item(0);
		           double price=Double.parseDouble(getCharacterDataFromElement(line));
		           
		           NodeList iurl = element.getElementsByTagName("image_url");
		           line = (Element) iurl.item(0);
		           String imgUrl= getCharacterDataFromElement(line);
		           
		           
		           if(categ==1){
		           ans.add(new MovieItem(id,sales,name,categ,scateg,price,imgUrl));
		           }
		           else{
			           ans.add(new BookItem(id,sales,name,categ,scateg,price,imgUrl));

		           }
		           
		        }
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		 return ans;
	}
		
	
	public void loadSearchResults(String query) {
		new CatalogRequest("GetProductListByName", "criteria=" + query).make(new RequestResponse() {
			public void handle(String response) {
			
						
				List<Item> items = new LinkedList<Item>();

				items=parseItems(response);
								
				app.setSearch(items);
				
			}

			
		});
	}
		
	
	public void signIn(String userName, String pass){
		
		new SecurityRequest("SignIn", "username="+userName+"&password="+pass).make(new RequestResponse() {
			
			public void handle(String response) {
				
				User user=parseUser(response);
				app.setUser(user);				
				app.setToken(user.getToken());
				
			}

			private User parseUser(String response) {
				
				User user = null;
			
				 try {
				        DocumentBuilderFactory dbf =
				            DocumentBuilderFactory.newInstance();
				        DocumentBuilder db = dbf.newDocumentBuilder();
				        InputSource is = new InputSource();
				        is.setCharacterStream(new StringReader(response));

				        Document doc = db.parse(is);
				        NodeList nodes = doc.getElementsByTagName("authentication");
				        Element element = (Element) nodes.item(0);
				           
				           NodeList tok = element.getElementsByTagName("token");
				           Element line = (Element) tok.item(0);
				           String token= getCharacterDataFromElement(line);
				           
				           
				         
				           NodeList usr = element.getElementsByTagName("user");
					       element = (Element) usr.item(0);

				           				           
				           int id=Integer.parseInt(element.getAttribute("id"));
				           String uname=element.getAttribute("username");
				           String name=element.getAttribute("name");
				           Date date=Date.valueOf(element.getAttribute("last_login_date"));
				           
				           
				           user=new User(token,id,uname,name,date);				           
				    }
				    catch (Exception e) {
				        e.printStackTrace();
				    }
				return user;
			
				
				
				
				
			}
		});
	}

	
	public void signOut(String uname, String token){
		
		new SecurityRequest("SignOut", "username="+uname+"&authentication_token="+token).make(new RequestResponse() {
			
			public void handle(String response) {
			
				app.setUser(null);
				
			}
		});
	}
	
	
	public void loadOrders(String uname, String token){
		
		new OrderRequest("GetOrderList", "username="+uname+"&authentication_token="+token).make(new RequestResponse() {

			public void handle(String response) {

				List<Order> orders = new LinkedList<Order>();


				orders=parseOrders(response);
				
				
				
				app.setOrders(orders);
		
			}

			private List<Order> parseOrders(String response) {
			
				
					List<Order> ans=new ArrayList<Order>();
					
					 try {
					        DocumentBuilderFactory dbf =
					            DocumentBuilderFactory.newInstance();
					        DocumentBuilder db = dbf.newDocumentBuilder();
					        InputSource is = new InputSource();
					        is.setCharacterStream(new StringReader(response));

					        Document doc = db.parse(is);
					        
					        
					        
					        NodeList nodes = doc.getElementsByTagName("order");
					        
					       

					        // iterate the categories
					        for (int i = 0; i < nodes.getLength(); i++) {
					        	
					        
					        	
					        	
					           Element element = (Element) nodes.item(i);
					           
					           int id=Integer.parseInt(element.getAttribute("id"));
					           
					           
					           
					           NodeList stat = element.getElementsByTagName("status");
					           Element line = (Element) stat.item(0);
					           int status=Integer.parseInt(getCharacterDataFromElement(line));
					           
					           if(status==2){

					           NodeList cd = element.getElementsByTagName("created_date");
					           line = (Element) cd.item(0);
					           String created_date= getCharacterDataFromElement(line);
					           
					           NodeList confd = element.getElementsByTagName("confirmed_date");
					           line = (Element) confd.item(0);
					           String confirmed_date= getCharacterDataFromElement(line);
					           					           
					           
					           NodeList ad = element.getElementsByTagName("address_id");
					           line = (Element) ad.item(0);
					           int address_id=0;
					           if(getCharacterDataFromElement(line)!=null){
					            address_id=Integer.parseInt(getCharacterDataFromElement(line));
					           }
					           else {
					        	    address_id=916;
					           }
					           
					           
					           Order order=new Order(id,created_date,confirmed_date);
					           getAddress(address_id,order);
					           ans.add(order);
					           }
					        }
					        
					    }
					    catch (Exception e) {
					        e.printStackTrace();
					    }
					 return ans;
				}				
				
			});
	}

	
	private void getAddress(int id,final Order order) {
		
		
		new OrderRequest("GetAddress", "username="+app.getUser().getUserName()+"&authentication_token="+app.getToken()+"&address_id="+id).make(new RequestResponse() {


			
			public void handle(String response) {

				order.addAddress(parseAddress(response));
			}

			private Address parseAddress(String response) {
				
				Address ans=new Address();
								
				
				 try {
				        DocumentBuilderFactory dbf =
				            DocumentBuilderFactory.newInstance();
				        DocumentBuilder db = dbf.newDocumentBuilder();
				        InputSource is = new InputSource();
				        is.setCharacterStream(new StringReader(response));

				        Document doc = db.parse(is);
				        NodeList nodes = doc.getElementsByTagName("address");
				        Element element = (Element) nodes.item(0);
				        
				        int id=Integer.parseInt(element.getAttribute("id"));
				           
				        NodeList fn = element.getElementsByTagName("full_name");
				        Element line = (Element) fn.item(0);
				        String full_name=getCharacterDataFromElement(line);
				        
				        NodeList a1 = element.getElementsByTagName("address_line_1");
				        line = (Element) a1.item(0);
				        String address_line_1=getCharacterDataFromElement(line);
				        
				        NodeList a2 = element.getElementsByTagName("address_line_2");
				        line = (Element) a2.item(0);
				        String address_line_2=getCharacterDataFromElement(line);
				        
				        NodeList ct = element.getElementsByTagName("city");
				        line = (Element) ct.item(0);
				        String city=getCharacterDataFromElement(line);
				        
				        
				        NodeList zc = element.getElementsByTagName("zip_code");
				        line = (Element) zc.item(0);
				        String zip_code=getCharacterDataFromElement(line);
				        
				        NodeList pn = element.getElementsByTagName("phone_number");
				        line = (Element) pn.item(0);
				        String phone_number=getCharacterDataFromElement(line);
				        
				        ans=new Address(full_name,address_line_1,address_line_2,city,zip_code,phone_number);
				        }
				    
				    catch (Exception e) {
				        System.out.println(response);;
				    }
				 return ans;
			}
		
		});
	
	}


	public List<? extends Map<String, ?>> getCategoriesAsMap() {
		String[] categoryFields = { "name" };
		loadCategories();
		List<Category> categories = app.getCategories();
		List<Map<String, String>> transformedCategories = new ArrayList<Map<String, String>>();
		for (Category c : categories) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(categoryFields[0], c.getName());
			transformedCategories.add(map);
		}
		return transformedCategories;
	}


	public String[] getCategoriesAsMapKeys() {
		String[] categoryFields = { "name" };
		return categoryFields;
	}
		
	

}

