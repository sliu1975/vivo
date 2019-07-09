package com.nplatform.dao;

import com.nplatform.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log, String>,JpaSpecificationExecutor<Log> {

}
