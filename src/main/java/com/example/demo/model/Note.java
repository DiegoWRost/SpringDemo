package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="note", schema = "public")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private long noteId;
    @Basic
    @Column(name = "title", nullable = false, length = -1)
    private String title;
    @Basic
    @Column(name = "contents", nullable = false, length = -1)
    private String contents;
    @JsonBackReference
    @JoinColumn(name = "owner", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person owner;

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}