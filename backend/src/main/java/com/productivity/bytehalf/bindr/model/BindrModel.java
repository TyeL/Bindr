package com.productivity.bytehalf.bindr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class BindrModel {
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 1, max = 40, message = "Title must be at between 1 and 40 characters")
    private String title;

    @JsonProperty("description")
    @Size(max = 104, message = "Description cannot be longer than 120 characters.")
    private String description;

    @JsonProperty("Snippets")
    private List<SnippetModel> snippets;

    @JsonProperty("num")
    private int num;

    public BindrModel(long id, String title, String description, List<SnippetModel> snippets, int num) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.snippets = snippets;
        this.num = num;
    }

    public BindrModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SnippetModel> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<SnippetModel> snippets) {
        this.snippets = snippets;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BindrModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", snippets=" + snippets +
                ", num=" + num +
                '}';
    }
}
