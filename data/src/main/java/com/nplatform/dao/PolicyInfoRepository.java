package com.nplatform.dao;

import com.nplatform.entity.PolicyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PolicyInfoRepository extends JpaRepository<PolicyInfo, String>,JpaSpecificationExecutor<PolicyInfo> {

    @Query("select c from PolicyInfo c ")
    Page<PolicyInfo> findDataByPage(Pageable pageRequest);

    @Query("select c from PolicyInfo c where c.id=:id")
    PolicyInfo findById(@Param("id") String id);
    
}
