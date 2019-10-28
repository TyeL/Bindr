package com.productivity.bytehalf.bindr.repository;

import com.productivity.bytehalf.bindr.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Note repository.
 */
@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findAllBySnippetId(long id);
}
