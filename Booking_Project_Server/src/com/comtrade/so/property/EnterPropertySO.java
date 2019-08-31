package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.cache.Cache;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;

public class EnterPropertySO extends GeneralSystemOperation<GenericList<GeneralDomain>> {

	@Override
	protected void runConcreteSO(GenericList<GeneralDomain> object) throws SQLException, Exception {
		
			GenericList<GeneralDomain>list=object;
			User user=new User();
			User_Info user_info=new User_Info();
			Property property=new Property();
		
				
			if(object.get(0) instanceof User) {
				
				user=(User) list.get(0);
				user_info=(User_Info) list.get(1);
				property=(Property) list.get(2);
				GenericList<GeneralDomain>listUser=new GenericList<GeneralDomain>();
				listUser.add(user);
				listUser.add(user_info);
				list.delete(user);
				list.delete(user_info);
				Cache.getInstance().addListInMap(user.getUsername(),listUser);
				Cache.getInstance().addListInMap(property.getName(),list);
				list.add(user);
				list.add(user_info);
				
			}else {
				 property=(Property) list.get(0);
				 Cache.getInstance().addListInMap(property.getName(),list);
			 
			}
	}

}
