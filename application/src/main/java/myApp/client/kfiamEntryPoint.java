package myApp.client;

import com.google.gwt.core.client.EntryPoint;

import myApp.client.vi.HomePage;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class kfiamEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		myApp.client.vi.HomePage login = new HomePage();
		login.open();  
	} 
}

