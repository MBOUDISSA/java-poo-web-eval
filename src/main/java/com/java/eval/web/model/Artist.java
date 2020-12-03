package com.java.eval.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {

    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ArtistId")
    public Long id;

    public String name;

    public Artist(){

    }

    public Artist(List<Album> albums, Long id, String name) {
        this.albums = albums;
        this.id = id;
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(albums, artist.albums) &&
                Objects.equals(id, artist.id) &&
                Objects.equals(name, artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name);
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", name='" + name + '\'' +
                '}';
    }

}
