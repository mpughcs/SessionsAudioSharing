package com.company.audioSharing.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "song")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Song {

    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private Integer length;

    @Column(name = "media_url")
    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }


    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", media_url='" + mediaUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) && Objects.equals(title, song.title) && Objects.equals(length, song.length) && Objects.equals(mediaUrl, song.mediaUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, length, mediaUrl);
    }
}
