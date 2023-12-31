package com.company.audioSharing.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "session")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Session {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;



    private int length;

    @Column(name = "media_url")
    private String mediaUrl;

    private String description;

    @OneToMany(mappedBy = "sessionId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Song> songs;


    public Session(){}
    public Session(String name, int length, String mediaUrl, String description) {
        this.name = name;
        this.length = length;
        this.mediaUrl = mediaUrl;
        this.description = description;
        this.songs = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return length == session.length && Objects.equals(id, session.id) && Objects.equals(name, session.name) && Objects.equals(mediaUrl, session.mediaUrl) && Objects.equals(description, session.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, length, mediaUrl, description);
    }
}
