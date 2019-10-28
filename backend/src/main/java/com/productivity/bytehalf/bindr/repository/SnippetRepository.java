package com.productivity.bytehalf.bindr.repository;

import com.productivity.bytehalf.bindr.entity.SnippetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Snippet repository.
 */
@Repository
public interface SnippetRepository extends JpaRepository<SnippetEntity, Long> {
    List<SnippetEntity> findAllByBindrId(long bindrId);
}
