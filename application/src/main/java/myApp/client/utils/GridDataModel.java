package myApp.client.utils;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface GridDataModel extends IsSerializable{
	public int i = 1;
//=======
//	
//	public int i = 0;
//>>>>>>> refs/heads/jhJang
	public void setKeyId(Long id);
	public Long getKeyId();
} 
