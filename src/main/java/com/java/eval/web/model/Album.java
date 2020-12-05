package com.java.eval.web.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AlbumId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artist artist;

    @Column(name="Title")
    private String title;


    public Album() {
    }

    public Album(Artist artist, Long id, String title) {
        this.artist = artist;
        this.id = id;
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(artist, album.artist) &&
                Objects.equals(id, album.id) &&
                Objects.equals(title, album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, id, title);
    }

    @Override
    public String toString() {
        return "Album{" +
                ", title='" + title + '\'' +
                '}';
    }

}
