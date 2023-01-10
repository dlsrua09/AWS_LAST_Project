package com.gogi1000.datecourse.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gogi1000.datecourse.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {
		//SELECT * FROM T_GGC_USER
		//	WHERE USER_ID = :userId
		//	  AND USER_PW = :userPw
		User findByUserIdAndUserPw(
				@Param("userId") String userId, 
				@Param("userPw") String userPw);
		
		Optional<User> findByUserIdAndUserMail(@Param("userId") String userId, @Param("userMail") String userMail);
		
		@Modifying
		@Query(value="UPDATE T_GGC_USER"
				+ "		SET USER_PW = :userPw"
				+ "		WHERE USER_ID = :userId", nativeQuery=true)
		void updateTempPw(@Param("userId") String userId, @Param("userPw") String userPw);
}
