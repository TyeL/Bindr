package com.productivity.bytehalf.bindr.service;

import com.productivity.bytehalf.bindr.entity.BindrEntity;
import com.productivity.bytehalf.bindr.entity.SnippetEntity;
import com.productivity.bytehalf.bindr.model.SnippetModel;
import com.productivity.bytehalf.bindr.repository.BindrRepository;
import com.productivity.bytehalf.bindr.repository.SnippetRepository;
import com.productivity.bytehalf.bindr.util.exception.RecordNotFoundException;
import org.eclipse.persistence.indirection.ValueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Snippet service.
 */
@Service
public class SnippetService {

    private SnippetRepository snippetRepository;
    private BindrRepository bindrRepository;
    private String DOES_NOT_EXIST = "does not exist";

    /**
     * Instantiates a new Snippet service.
     *
     * @param snippetRepository the snippet repository
     * @param bindrRepository   the bindr repository
     */
    @Autowired
    public SnippetService(SnippetRepository snippetRepository, BindrRepository bindrRepository) {
        this.snippetRepository = snippetRepository;
        this.bindrRepository = bindrRepository;
    }

    /**
     * Find all by bindr id list.
     *
     * @param bindrId the bindr id
     * @return the list
     * @throws RecordNotFoundException the record not found exception
     */
    public List<SnippetEntity> findAllByBindrId(long bindrId) throws RecordNotFoundException {
        return snippetRepository.findAllByBindrId(bindrId);
    }

    /**
     * Find by id snippet entity.
     *
     * @param id the id
     * @return the snippet entity
     */
    public SnippetEntity findById(long id) {
        return snippetRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Snippet " + id + DOES_NOT_EXIST
                ));
    }

    /**
     * Save snippet entity.
     *
     * @param bindrId      the bindr id
     * @param snippetModel the snippet model
     * @return the snippet entity
     */
    public SnippetEntity save(long bindrId, SnippetModel snippetModel) {

        SnippetEntity snippet = new SnippetEntity();
        BindrEntity bindr = bindrRepository
                .findById(bindrId).orElseThrow(() -> new RecordNotFoundException(
                        "Bindr " + bindrId + DOES_NOT_EXIST
                ));

        snippet.setId(snippetModel.getId());
        snippet.setTitle(snippetModel.getTitle());
        snippet.setTags(snippetModel.getTags());
        snippet.setDescription(snippetModel.getDescription());
        snippet.setLanguage(snippetModel.getLanguage());
        snippet.setSnippet(snippetModel.getSnippet());
        snippet.setBindr(bindr);

        List<SnippetEntity> snippets = snippetRepository.findAllByBindrId(bindrId);
        bindr.setNum(snippets.size() + 1);

        bindrRepository.save(bindr);

        System.out.println("BBBBBBBRINDR " +bindr.getNum());

        return snippetRepository.save(snippet);



    }

    /**
     * Update snippet entity.
     *
     * @param id   the snippet id
     * @param snippetModel the snippet model
     * @return the snippet entity
     * @throws RecordNotFoundException the record not found exception
     */
    public SnippetEntity update(long id,
                                SnippetModel snippetModel) throws RecordNotFoundException {
        SnippetEntity snippet = snippetRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Snippet " + id + DOES_NOT_EXIST
                ));

        snippet.setTitle(snippetModel.getTitle());
        snippet.setTags(snippetModel.getTags());
        snippet.setDescription(snippetModel.getDescription());
        snippet.setLanguage(snippetModel.getLanguage());
        snippet.setSnippet(snippetModel.getSnippet());

        return snippetRepository.save(snippet);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws RecordNotFoundException the record not found exception
     */
    public void delete(long id) throws RecordNotFoundException {
        snippetRepository.deleteById(id);
    }
}
