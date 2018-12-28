package myApp.client.vi.sys.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface Sys13_YearMonthModelProperties extends PropertyAccess<Sys13_YearMonthModel> {

	ModelKeyProvider<Sys13_YearMonthModel> keyId();

	ValueProvider<Sys13_YearMonthModel,	Long>	yearMonthId();
	ValueProvider<Sys13_YearMonthModel,	String>	yearMonth();
	ValueProvider<Sys13_YearMonthModel,	String>	yearMonthKor();
	ValueProvider<Sys13_YearMonthModel,	String>	yearMonthEng();
	ValueProvider<Sys13_YearMonthModel,	String>	yearMonthPoint();

}
