package com.productivity.bytehalf.bindr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

public class SnippetModel {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 1, max = 40, message = "Title must be at between 1 and 40 characters")
    private String title;

    @JsonProperty("tags")
    @Size(max = 40, message = "Tags cannot be longer than 40 characters")
    private String tags;

    @JsonProperty("description")
    @Size(max = 120, message = "Description cannot be longer than 120 characters")
    private String description;

    @JsonProperty("language")
    @NotNull(message = "Language cannont be null")
    @NotEmpty(message = "Language cannot be empty")
    private String language;

    @JsonProperty("bindr")
    private BindrModel bindr;

    @JsonProperty("snippet")
    private String snippet;

    @JsonProperty("notes")
    @JsonIgnore
    private List<NoteModel> notes;

    public SnippetModel(long id, String title, String tags, String description,
                        String language, BindrModel bindr, String snippet,
                        List<NoteModel> notes) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.language = language;
        this.bindr = bindr;
        this.snippet = snippet;
        this.notes = notes;
    }

    public SnippetModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BindrModel getBindr() {
        return bindr;
    }

    public void setBindr(BindrModel bindr) {
        this.bindr = bindr;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "SnippetModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", bindr=" + bindr +
                ", snippet='" + snippet + '\'' +
                ", notes=" + notes +
                '}';
    }
}
