package com.nirsb.account.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirsb.account.dao.UserDAO;
import com.nirsb.account.exception.UserNotFoundException;
import com.nirsb.account.model.User;

@Service
public class UserService {
    private static Mapper mapper = new DozerBeanMapper();
	@Autowired
	UserDAO userDao;
	
	public com.nirsb.account.model.dto.User createUser(User user) {
		
		com.nirsb.account.model.dto.User userObject =  
		    mapper.map(user, com.nirsb.account.model.dto.User.class);
		List<com.nirsb.account.model.dto.Address> addressDtoObject = new ArrayList<com.nirsb.account.model.dto.Address>();
		List<com.nirsb.account.model.Address> addressList = user.getAddresses();
		if(addressList != null) {
			for(com.nirsb.account.model.Address address: addressList) {
				addressDtoObject.add(mappingAddress(address));
			}
			userObject.setAddresses(addressDtoObject);
		}
		com.nirsb.account.model.dto.User newUserObject = userDao.save(userObject);
		if( newUserObject != null) {
			return newUserObject;
		}
		return null;
	}
	
	private com.nirsb.account.model.dto.Address mappingAddress(com.nirsb.account.model.Address address){
		com.nirsb.account.model.dto.Address addressObject = 
				mapper.map(address,com.nirsb.account.model.dto.Address.class);
		return addressObject;
	}
	
	public com.nirsb.account.model.dto.User getUser(long id){
		com.nirsb.account.model.dto.User user = userDao.getOne(id);
		if(user == null) {
			throw new UserNotFoundException("User Not Found in DB: "+id);
		}
		return user;
	}
	
}
