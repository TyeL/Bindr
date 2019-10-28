package com.productivity.bytehalf.bindr.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Size(min = 1, max = 40)
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "snippet_id")
    private SnippetEntity snippet;

    public NoteEntity(String title, String note, SnippetEntity snippet) {
        this.title = title;
        this.note = note;
        this.snippet = snippet;
    }

    public NoteEntity() {
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

    public SnippetEntity getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetEntity snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", snippet=" + snippet +
                '}';
    }
}
