package myApp.client;

import com.google.gwt.core.client.EntryPoint;

import myApp.client.vi.ContactPointer;
import myApp.client.vi.LoginPage;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class kfiaEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		myApp.client.vi.ContactPointer login = new ContactPointer();
//		myApp.client.vi.LoginPage login = new LoginPage();
		login.open();  
	} 
}
