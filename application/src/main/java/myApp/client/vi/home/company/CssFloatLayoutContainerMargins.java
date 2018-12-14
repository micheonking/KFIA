package myApp.client.vi.home.company;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.ResizeEndEvent;
import com.sencha.gxt.widget.core.client.event.ResizeEndEvent.ResizeEndHandler;

public class CssFloatLayoutContainerMargins implements EntryPoint {

  @Override
  public void onModuleLoad() {
    final SimpleContainer wrap = new SimpleContainer();

    final CssFloatLayoutContainer con = new CssFloatLayoutContainer();
    con.setBorders(true);
    con.getElement().getStyle().setBackgroundColor("#FFF");
    con.add(getRowWidget("100%, 10px margin"), new CssFloatData(1, new Margins(10)));
    con.add(getRowWidget("150px, no margin"), new CssFloatData(150));

    con.add(getRowWidget("100% no margin"), new CssFloatData(1));

    con.add(getRowWidget("50%, 10px margin"), new CssFloatData(.5, new Margins(10)));
    con.add(getRowWidget("50%, no margin"), new CssFloatData(.5));
    con.add(getRowWidget("150px, 10px margin"), new CssFloatData(150, new Margins(10)));

    CssFloatData layoutData1 = new CssFloatData(300, new Margins(10));
    layoutData1.setClear(true);
    con.add(getRowWidget("300px, 10px margin, start of new line"), layoutData1);

    CssFloatData layoutData2 = new CssFloatData(300);
    layoutData2.setClear(true);
    con.add(getRowWidget("300px, no margin, start of new line"), layoutData2);
    con.add(getRowWidget("150px, 10px margin"), new CssFloatData(150, new Margins(10)));
    con.add(getRowWidget("150px, 10px margin"), new CssFloatData(150, new Margins(10)));
    con.add(getRowWidget("150px, 10px margin"), new CssFloatData(150, new Margins(10)));

    CssFloatData layoutData3 = new CssFloatData();
    layoutData3.setClear(true);
    con.add(getRowWidget("start of line, fixed size via -1 (280px)", 280), layoutData3);
    con.add(getRowWidget("100%, no margin"), new CssFloatData(1));

    CssFloatData layoutData4 = new CssFloatData(1);
    layoutData4.setClear(true);
    con.add(getRowWidget("100%, start of line"), layoutData4);
    con.add(getRowWidget("fixed size via -1 (200px)", 200));


    wrap.add(con, new MarginData(10));
    wrap.setPixelSize(800, -1);

    new Resizable(wrap, Dir.E).addResizeEndHandler(new ResizeEndHandler() {
      @Override
      public void onResizeEnd(ResizeEndEvent event) {
        wrap.setHeight(-1);
        con.setHeight(-1);
      }
    });

    RootPanel.get().add(wrap);
  }

  private Widget getRowWidget(String text, int width) {
    SimpleContainer w = new SimpleContainer();
    if (width != -1) {
      w.setWidth(width);
    }
    w.setBorders(true);
    w.add(new Label(text), new MarginData(10));
    return w;
  }

  public Widget getRowWidget(String text) {
    return getRowWidget(text, -1);
  }

}