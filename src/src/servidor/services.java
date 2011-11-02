package servidor;

import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import tpAndroid.main.Address;
import tpAndroid.main.App;
import tpAndroid.main.Category;
import tpAndroid.main.Item;
import tpAndroid.main.Order;
import tpAndroid.main.SubCategory;






public class services {
	
	
	int langid = 1;
	App app;
	
	public services(App app) {
		this.app=app;
	}


	public void loadCategories() {
		//mainFrame.navbar.cats.disable();
		
		new catalogRequest("GetCategoryList", "language_id=" + langid).make(new RequestResponse() {
			
		
			public void handle(Document response) {
				
				List<Category> categories = new LinkedList<Category>();

				NodeList catnodes = response.getElementsByTagName("category");

				for (int i = 0; i < catnodes.getLength(); i++){
					categories.add(new Category((Element) catnodes.item(i)));
					
				}
				
				app.setCategories(categories);
			/*	SwingUtilities.invokeLater(new ObjectAction<List<Category>>(categories) {

					public void run() {
						mainFrame.navbar.populateCategories(object);
						mainFrame.navbar.cats.enable();
					}

				}); */
			}
		});
	}

	public void loadSubcategories(int category_id) {
	//	mainFrame.navbar.subcats.disable();
		
		new catalogRequest("GetSubcategoryList", "language_id=" + langid + "&category_id=" + category_id).make(new RequestResponse() {
			public void handle(Document response) {
			
				List<SubCategory> subcategories = new LinkedList<SubCategory>();

				NodeList catnodes = response.getElementsByTagName("subcategory");

				for (int i = 0; i < catnodes.getLength(); i++)
					subcategories.add(new SubCategory((Element) catnodes.item(i)));

				/*SwingUtilities.invokeLater(new ObjectAction<List<Subcategory>>(subcategories) {

					public void run() {
						mainFrame.navbar.populateSubcategories(object);
						mainFrame.navbar.subcats.enable();
					}

				});*/
			}
		});
	}

	public void loadProducts(int category_id, int subcategory_id) {
		//mainFrame.sidebar.showLoading();
		
		final RequestResponse rcb = new RequestResponse() {
			
			public void handle(Document response) {
			
				NodeList nodelist = response.getElementsByTagName("product");

				for (int i = 0; i < nodelist.getLength(); i++) {
					Item item = new Item((Element) nodelist.item(i));
					app.getItemsList().add(item);
					/*
					 * SwingUtilities.invokeLater(new
					 * ObjectAction<Product>(product) {
					 * 
					 * public void run() { fmain.catalog.addProduct(object,
					 * true); }
					 * 
					 * });
					 */
				}
			}
		};

		if (category_id != 0) {
			if (subcategory_id == 0) {
				new catalogRequest("GetProductListByCategory", "language_id=" + langid + "&category_id=" + category_id).make(rcb);
				//App.stats.incrementStat("Category-" + category_id);
			} else {
				new catalogRequest("GetProductListBySubcategory", "language_id=" + langid + "&category_id=" + category_id + "&subcategory_id=" + subcategory_id).make(rcb);
			//	mainFrame.stats.incrementStat("SubCategory-" + subcategory_id);
			}
		}
	}

	public void loadSearchResults(String query) {
	//	App.sidebar.showLoading();
		new catalogRequest("GetProductListByName", "criteria=" + query).make(new RequestResponse() {
			public void handle(Document response) {
				//App.showSearchContent();
				//App.search.clearProducts();
				NodeList nodelist = response.getElementsByTagName("product");

				for (int i = 0; i < nodelist.getLength(); i++) {
					Item item = new Item((Element) nodelist.item(i));
					app.getSearch().add(item);
				}
				//App.sidebar.showLogo();
			}

		});
	}

	
	//VERRRRRRR
	public void showProductDetail(int product_id) {
		//App.sidebar.showLoading();
		new catalogRequest("GetProduct", "product_id=" + product_id).make(new RequestResponse() {
			public void handle(Document response) {
				
				Element element = (Element) response.getElementsByTagName("product").item(0);
				Item p = new Item(element);
				//App.stats.incrementStat(p.name);
				
				//String message = "<html>";
				
			/*	for (String key : p.info.keySet()) {
					if (!"image_url".equals(key)) {
						message += "<b>" + context.getResourceMap().getString(key) + "</b> " + p.info.get(key) + "<br />";
					}
				}*/
				//App.sidebar.showLogo();
				//JOptionPane.showMessageDialog(mainFrame, message, p.name, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(p.image));

			}

		});

}

	public void signIn(String userName, String pass){
		
		new securityRequest("SignIn", "username="+userName+"&password="+pass).make(new RequestResponse() {
			
			@Override
			public void handle(Document response) {
				String token = response.getElementsByTagName("token").item(0).getNodeValue();
				String username=response.getElementById("user").getAttribute("username");
				app.setToken(token);
				app.setUsername(username);
				
			}
		});
	}

	public void signOut(String uname, String token){
		
		new securityRequest("SignOut", "username="+uname+"&authentication_token="+token).make(new RequestResponse() {
			
			@Override
			public void handle(Document response) {
			
				app.setUsername(null);
				
			}
		});
	}

	public void loadOrders(String uname, String token){
		
		new orderRequest("GetOrderList", "username="+uname+"&authentication_token="+token).make(new RequestResponse() {

			@Override
			public void handle(Document response) {

				List<Order> orders = new LinkedList<Order>();

				NodeList catnodes = response.getElementsByTagName("orders");
				
				for (int i = 0; i < catnodes.getLength(); i++){
					orders.add(new Order((Element) catnodes.item(i)));
				}
				
				app.setOrders(orders);
			}
		
	});
	}

	public void loadAddress(String uname, String token, int addId){
		
		new orderRequest("GetAddressList", "username="+uname+"&authentication_token="+token).make(new RequestResponse() {

			@Override
			public void handle(Document response) {
				
				List<Address> adds = new LinkedList<Address>();

				NodeList catnodes = response.getElementsByTagName("address");
				
				for (int i = 0; i < catnodes.getLength(); i++){
					adds.add(new Address((Element) catnodes.item(i)));
				}
				
				app.setAddreses(adds);
			}
				
			});
	
	}
}

