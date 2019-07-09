package com.nplatform.dao;

import com.nplatform.entity.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String>,JpaSpecificationExecutor<CompanyInfo> {

    @Query("select c from CompanyInfo c ")
    Page<CompanyInfo> findDataByPage(Pageable pageRequest);

    @Query("select c from CompanyInfo c where c.id=:id")
    CompanyInfo findById(@Param("id") String id);

}
