package com.productivity.bytehalf.bindr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bindrs")
public class BindrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Size(min = 1, max = 40)
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    @Size(max = 104)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "bindr", cascade = CascadeType.ALL)
    private List<SnippetEntity> snippets;

    @Column(name = "num")
    private int num = 0;

    public BindrEntity(String title, String description, List<SnippetEntity> snippets, int num) {
        this.title = title;
        this.description = description;
        this.snippets = snippets;
        this.num = num;
    }

    public BindrEntity() {
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

    public List<SnippetEntity> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<SnippetEntity> snippets) {
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
        return "BindrEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", snippets=" + snippets +
                '}';
    }
}
