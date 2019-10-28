package com.productivity.bytehalf.bindr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoteModel {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 1, max = 40, message = "Title must be at between 1 and 40 characters")
    private String title;

    @JsonProperty("note")
    private String note;

    @JsonProperty("snippet")
    private SnippetModel snippet;

    public NoteModel(long id, String title, String note, SnippetModel snippet) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.snippet = snippet;
    }

    public NoteModel() {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SnippetModel getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetModel snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", snippet=" + snippet +
                '}';
    }
}
