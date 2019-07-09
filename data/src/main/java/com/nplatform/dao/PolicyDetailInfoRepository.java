package com.nplatform.dao;

import com.nplatform.entity.PolicyDetailInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PolicyDetailInfoRepository extends JpaRepository<PolicyDetailInfo, Integer>,JpaSpecificationExecutor<PolicyDetailInfo> {

    @Query("select c from PolicyDetailInfo c ")
    Page<PolicyDetailInfo> findDataByPage(Pageable pageRequest);

    @Query("select c from PolicyDetailInfo c where c.id=:id")
    PolicyDetailInfo findById(@Param("id") String id);

    @Query("select c from PolicyDetailInfo c where c.policy_id=:policy_id")
    PolicyDetailInfo findByPolicy_id(@Param("policy_id") String policy_id);
    
}
