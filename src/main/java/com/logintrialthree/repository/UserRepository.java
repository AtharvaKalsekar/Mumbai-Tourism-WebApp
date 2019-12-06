package com.logintrialthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logintrialthree.model.User;

@Repository("userRepository")
public interface UserRepository  extends JpaRepository<User, Long>
{
	User findByEmail(String email);
	@Modifying
	@Query(value = "update User u set u.wallet = :balance where u.id = :id", nativeQuery = true)
	@Transactional
	void updateUserBalance(@Param("id") int id, @Param("balance")float balance);
}
