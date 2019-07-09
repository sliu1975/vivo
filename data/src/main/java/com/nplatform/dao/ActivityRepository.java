package com.nplatform.dao;

import com.nplatform.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, String>,JpaSpecificationExecutor<Activity> {

    @Query("select c from Activity c order by c.created_time desc")
    Page<Activity> findDataByPage(Pageable pageRequest);

    @Query("select c from Activity c where c.id=:id")
    Activity findById(@Param("id") String id);
    
}
