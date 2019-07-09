package com.nplatform.dao;

import com.nplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>,JpaSpecificationExecutor<User> {
    @Query("select c from User c ")
    Page<User> findUser(Pageable pageRequest);

    @Query("select c from User c where c.userName=:userName")
    User findByUserName(@Param("userName") String userName);

    @Query("select c from User c where c.id=:id")
    User findById(@Param("id") Integer id);

}
