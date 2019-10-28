package com.productivity.bytehalf.bindr.service;

import com.productivity.bytehalf.bindr.entity.NoteEntity;
import com.productivity.bytehalf.bindr.entity.SnippetEntity;
import com.productivity.bytehalf.bindr.model.NoteModel;
import com.productivity.bytehalf.bindr.repository.NoteRepository;
import com.productivity.bytehalf.bindr.repository.SnippetRepository;
import com.productivity.bytehalf.bindr.util.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Note service.
 */
@Service
public class NoteService {

    private NoteRepository noteRepository;
    private SnippetRepository snippetRepository;
    private String DOES_NOT_EXIST = "does not exist";

    /**
     * Instantiates a new Note service.
     *
     * @param noteRepository    the note repository
     * @param snippetRepository the snippet repository
     */
    @Autowired
    public NoteService(NoteRepository noteRepository,
                       SnippetRepository snippetRepository) {
        this.noteRepository = noteRepository;
        this.snippetRepository = snippetRepository;
    }

    /**
     * Find all by snippet id list.
     *
     * @param snippetId the snippet id
     * @return the list of snippet notes
     */
    public List<NoteEntity> findAllBySnippetId(long snippetId) {
        return noteRepository.findAllBySnippetId(snippetId);
    }

    /**
     * Find by id note entity.
     *
     * @param id the id
     * @return the snippet note
     */
    public NoteEntity findById(long id) {
        return noteRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Note " + id + DOES_NOT_EXIST
                ));
    }

    /**
     * Save note entity.
     *
     * @param snippetId the snippet id
     * @param noteModel the note model
     * @return the snippet note
     */
    public NoteEntity save(long snippetId, NoteModel noteModel) {
        NoteEntity note = new NoteEntity();
        SnippetEntity snippet = snippetRepository
                .findById(snippetId).orElseThrow(() -> new RecordNotFoundException(
                "Snippet " + snippetId + DOES_NOT_EXIST
        ));

        note.setId(noteModel.getId());
        note.setTitle(noteModel.getTitle());
        note.setNote(noteModel.getNote());
        note.setSnippet(snippet);

        return noteRepository.save(note);
    }

    /**
     * Update note entity.
     *
     * @param id        the id
     * @param noteModel the note model
     * @return the snippet note
     */
    public NoteEntity update(long id, NoteModel noteModel) {
        NoteEntity note = noteRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Note " + id + DOES_NOT_EXIST
                ));

        note.setTitle(noteModel.getTitle());
        note.setNote(noteModel.getNote());

        return noteRepository.save(note);

    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws RecordNotFoundException the record not found exception
     */
    public void delete(long id) throws RecordNotFoundException {
        noteRepository.deleteById(id);
    }
}
