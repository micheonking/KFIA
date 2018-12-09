package myApp.client.vi.home.product;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

public class SmartHeild extends ContentPanel {

	public SmartHeild() {

		this.setHeaderVisible(false);

		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		HBoxLayoutContainer searchContainer = new HBoxLayoutContainer();
		searchContainer.setPadding(new Padding(5));
		searchContainer.setPack(BoxLayoutPack.CENTER);
		BoxLayoutData boxLayout = new BoxLayoutData(new Margins(0, 5, 5, 0));
		TextField searchText = new TextField();
		TextButton searchButton = new TextButton("Search");
		searchContainer.add(searchText, boxLayout);
		searchContainer.add(searchButton, boxLayout);
		searchContainer.setBorders(true);
		layout.add(searchContainer);

	}
}