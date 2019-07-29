package com.library.api.repository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.api.model.Users;
@Transactional
@Repository
public interface LibraryRepository   {
	
	 String save(Users users);

	 String update(Users users);





}
	

