package com.productivity.bytehalf.bindr.repository;

import com.productivity.bytehalf.bindr.entity.BindrEntity;
import com.productivity.bytehalf.bindr.model.BindrModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Bindr repository.
 */
@Repository
public interface BindrRepository extends JpaRepository<BindrEntity, Long> {
}
