package com.productivity.bytehalf.bindr.controller;

import com.productivity.bytehalf.bindr.entity.BindrEntity;
import com.productivity.bytehalf.bindr.entity.NoteEntity;
import com.productivity.bytehalf.bindr.entity.SnippetEntity;
import com.productivity.bytehalf.bindr.model.BindrModel;
import com.productivity.bytehalf.bindr.model.NoteModel;
import com.productivity.bytehalf.bindr.model.SnippetModel;
import com.productivity.bytehalf.bindr.service.BindrService;
import com.productivity.bytehalf.bindr.service.NoteService;
import com.productivity.bytehalf.bindr.service.SnippetService;
import com.productivity.bytehalf.bindr.util.exception.RecordNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bindrs")
@Api(value = "Bindr API", tags = "Code Snippets & Notes")
public class BindrController {

    private Logger logger = LoggerFactory.getLogger(BindrController.class);

    private BindrService bindrService;
    private SnippetService snippetService;
    private NoteService noteService;

    private String BINDR_ID_LOGGER_MESSAGE = "Bindr id is ";

    @Autowired
    public BindrController(BindrService bindrService, SnippetService
            snippetService, NoteService noteService) {
        this.snippetService = snippetService;
        this.bindrService = bindrService;
        this.noteService = noteService;
    }

    @ApiOperation(value = "Get list of bindrs", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bindrs"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture getBindrs() {
        return CompletableFuture.completedFuture(bindrService.findAll());

    }


    @ApiOperation(value = "Get a individual bindr by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bindrs"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture getBindrById(@PathVariable(value = "id"
    ) long id) {
        logger.info(String.format(BINDR_ID_LOGGER_MESSAGE, id));
        return CompletableFuture.completedFuture(bindrService.findById(id));
    }

    @ApiOperation(value = "Create a new bindr")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved bindr"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PostMapping
    public CompletableFuture createBindr(@Valid @RequestBody BindrModel bindrModel) {
        logger.info(String.format("Bindr %s: ", bindrModel));
        return CompletableFuture.completedFuture(bindrService.save(bindrModel));
    }

    @ApiOperation(value = "Update bindr details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated bindr details"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture updateBindr(@PathVariable(value = "id") long id,
                                         @Valid @RequestBody BindrModel bindrModel) throws RecordNotFoundException {
        logger.info(String.format(BINDR_ID_LOGGER_MESSAGE, id));
        logger.info(String.format("Bindr %s: ", bindrModel));
        return CompletableFuture.completedFuture(bindrService.update(id, bindrModel));
    }

    @ApiOperation(value = "Delete a bindr by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted bindr"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture deleteBindr(@PathVariable(value = "id") long id) {

        logger.info(String.format(BINDR_ID_LOGGER_MESSAGE, id));

        BindrEntity bindr = bindrService.findById(id);

        bindrService.delete(bindr.getId());

        return CompletableFuture.completedFuture(bindr);
    }

    @ApiOperation(value = "Get list of bindr snippets", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved snippets"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(value = "/{id}/snippets", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture<List<SnippetEntity>> getSnippets(@PathVariable(value = "id"
    ) long bindrId) {
        logger.debug(String.format(BINDR_ID_LOGGER_MESSAGE, bindrId));

        return CompletableFuture.completedFuture(snippetService.findAllByBindrId(bindrId));
    }

    @ApiOperation(value = "Get a individual snippet by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved snippet"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(value = "/{id}/snippets/{snippetId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture getSnippetById(@PathVariable(value = "id") long bindrId,
                                            @PathVariable(value =
                                                    "snippetId") long snippetId) {
        return CompletableFuture.completedFuture(snippetService.findById(snippetId));
    }

    @ApiOperation(value = "Create a new snippet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved snippet"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PostMapping(value = "/{id}/snippets")
    public CompletableFuture createSnippet(@Valid @PathVariable(value = "id") long id,
                                           @RequestBody SnippetModel snippetModel) {
        return CompletableFuture.completedFuture(snippetService.save(id,
                snippetModel));
    }

    @ApiOperation(value = "Update snippet details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated snippet details"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PutMapping(value = "/{id}/snippets/{snippetId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture updateSnippet(@Valid @PathVariable(value = "id") long bindrId,
                                           @PathVariable(value = "snippetId") long snippetId,
                                           @RequestBody SnippetModel snippetModel) {

        return CompletableFuture.completedFuture(snippetService.update(
                snippetId,
                snippetModel));
    }

    @ApiOperation(value = "Delete a snippet by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted snippet"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @DeleteMapping(value = "/{id}/snippets/{snippet_id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture deleteSnippet(@PathVariable(value = "id") long bindrId,
                                           @PathVariable(value = "snippet_id") long snippetId) {
        SnippetEntity snippet = snippetService.findById(snippetId);

        snippetService.delete(snippet.getId());

        BindrEntity bindr = bindrService.findById(bindrId);

        BindrModel bindrModel = new BindrModel();
        int num = bindr.getNum() - 1;
        bindrModel.setNum(num);

        bindrService.update(bindrId, bindrModel);



        return CompletableFuture.completedFuture(snippet);
    }

    @ApiOperation(value = "Get list of snippet notes", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved snippet notes"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(value = "/{id}/snippets/{snippetId}/notes", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture<List<NoteEntity>> getNotes(@PathVariable(value = "id"
    ) long bindrId, @PathVariable(value = "snippetId") long snippetId) {
        logger.debug(String.format(BINDR_ID_LOGGER_MESSAGE, bindrId));

        return CompletableFuture.completedFuture(noteService.findAllBySnippetId(snippetId));
    }

    @ApiOperation(value = "Get a individual note by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved note"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @GetMapping(value = "/{id}/snippets/{snippetId}/notes/{noteId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    @Async("taskExecutor")
    public CompletableFuture getNoteById(@PathVariable(value = "id") long bindrId,
                                            @PathVariable(value =
                                                    "snippetId") long snippetId,
                                         @PathVariable(value = "noteId") long noteId) {
        return CompletableFuture.completedFuture(noteService.findById(noteId));
    }

    @ApiOperation(value = "Create a new note")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved note"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PostMapping(value = "/{id}/snippets/{snippetId}/notes")
    public CompletableFuture createNote(@Valid @PathVariable(value = "id") long id,
                                        @PathVariable(value = "snippetId") long snippetId,
                                           @RequestBody NoteModel noteModel) {
        return CompletableFuture.completedFuture(noteService.save(snippetId,
                noteModel));
    }

    @ApiOperation(value = "Update note details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated note details"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @PutMapping(value = "/{id}/snippets/{snippetId}/notes/{noteId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture updateNote(@Valid @PathVariable(value = "id") long bindrId,
                                        @PathVariable(value = "snippetId") long snippetId,
                                        @PathVariable(value = "noteId") long noteId,
                                        @RequestBody NoteModel noteModel) {

        return CompletableFuture.completedFuture(noteService.update(
                noteId, noteModel));
    }

    @ApiOperation(value = "Delete a note by specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted note"),
            @ApiResponse(code = 401, message = "You are not authorized to view the " +
                    "resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " +
                    "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach " +
                    "is not found")

    })
    @CrossOrigin
    @DeleteMapping(value = "/{id}/snippets/{snippetId}/notes/{noteId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture deleteSnippet(@PathVariable(value = "id") long bindrId,
                                           @PathVariable(value = "snippetId") long snippetId,
                                           @PathVariable(value = "noteId") long noteId) {
        NoteEntity note = noteService.findById(noteId);

        noteService.delete(note.getId());

        return CompletableFuture.completedFuture(note);
    }
}
