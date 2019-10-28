package com.productivity.bytehalf.bindr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "snippets")
public class SnippetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Size(min = 1, max = 40)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 40)
    @Column(name = "tags")
    private String tags;

    @Size(max = 120)
    @Column(name = "description")
    private String description;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "snippet")
    private String snippet;

    @ManyToOne
    @JoinColumn(name = "bindr_id")
    private BindrEntity bindr;

    @JsonIgnore
    @OneToMany(mappedBy = "snippet", cascade = CascadeType.ALL)
    private List<NoteEntity> notes;

    public SnippetEntity(String title, String tags, String description, String language,
                         String snippet, BindrEntity bindr, List<NoteEntity> notes) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.language = language;
        this.snippet = snippet;
        this.bindr = bindr;
        this.notes = notes;
    }

    public SnippetEntity() {
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

    public BindrEntity getBindr() {
        return bindr;
    }

    public void setBindr(BindrEntity bindr) {
        this.bindr = bindr;
    }

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "SnippetEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", snippet='" + snippet + '\'' +
                ", bindr=" + bindr +
                ", notes=" + notes +
                '}';
    }
}
