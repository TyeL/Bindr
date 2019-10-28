package com.productivity.bytehalf.bindr.service;

import com.productivity.bytehalf.bindr.entity.BindrEntity;
import com.productivity.bytehalf.bindr.model.BindrModel;
import com.productivity.bytehalf.bindr.repository.BindrRepository;
import com.productivity.bytehalf.bindr.util.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Bindr service.
 */
@Service
public class BindrService {

    private BindrRepository bindrRepository;

    /**
     * Instantiates a new Bindr service.
     *
     * @param bindrRepository the bindr repository
     */
    @Autowired
    public BindrService(BindrRepository bindrRepository) {
        this.bindrRepository = bindrRepository;
    }

    /**
     * Find all list.
     *
     * @return all bindrs
     */
    public List<BindrEntity> findAll() {
        return bindrRepository.findAll();
    }

    /**
     * Find by id bindr entity.
     *
     * @param id the id
     * @return a bindr specified by id
     */
    public BindrEntity findById(long id) {
        return bindrRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Bindr " + id + "does not exist"
                ));
    }

    /**
     * Save bindr entity.
     *
     * @param bindrModel the bindr model
     * @return the bindr
     */
    public BindrEntity save(BindrModel bindrModel) {
        BindrEntity bindr = new BindrEntity();
        bindr.setId(bindrModel.getId());
        bindr.setTitle(bindrModel.getTitle());
        bindr.setDescription(bindrModel.getDescription());
        return bindrRepository.save(bindr);
    }

    /**
     * Update bindr entity.
     *
     * @param id         the id
     * @param bindrModel the bindr model
     * @return the updated bindr
     */
    public BindrEntity update(long id, BindrModel bindrModel) {
        BindrEntity bindr = bindrRepository
                .findById(id).orElseThrow(() -> new RecordNotFoundException(
                        "Bindr " + id + "does not exist"
                ));

        bindr.setTitle(bindrModel.getTitle());
        bindr.setDescription(bindrModel.getDescription());
        bindr.setNum(bindrModel.getNum());

        return bindrRepository.save(bindr);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws RecordNotFoundException the record not found exception
     */
    public void delete(long id) throws RecordNotFoundException {
        bindrRepository.deleteById(id);
    }
}
