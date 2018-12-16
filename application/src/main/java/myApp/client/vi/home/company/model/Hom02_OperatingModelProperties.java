package myApp.client.vi.home.company.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface Hom02_OperatingModelProperties extends PropertyAccess<Hom02_OperatingModel> {
	
	ModelKeyProvider<Hom02_OperatingModel> keyId();

	ValueProvider<Hom02_OperatingModel,	Long>	operatingId();
	ValueProvider<Hom02_OperatingModel,	Long>	companyId();
	ValueProvider<Hom02_OperatingModel,	Long>	orgCodeId();
	ValueProvider<Hom02_OperatingModel,	String>	nameTitle();
	ValueProvider<Hom02_OperatingModel,	String>	chargeStockFirm();
	ValueProvider<Hom02_OperatingModel,	String>	majorCareer();
	ValueProvider<Hom02_OperatingModel,	String>	academicCertificate();
	ValueProvider<Hom02_OperatingModel,	String>	contactInfomation();
	ValueProvider<Hom02_OperatingModel,	String>	numericalOrder();

}
