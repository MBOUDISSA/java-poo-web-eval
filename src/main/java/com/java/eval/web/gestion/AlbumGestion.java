package com.java.eval.web.gestion;

import com.java.eval.web.model.Album;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class AlbumGestion {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public Album addAlbum(Album album){
        /*if (albumRepository.findByTitle(album.getTitle()) != null) {
            //404
            throw new EntityExistsException("Album déjà existant !");
        }
        if (artistRepository.findByName(album.getArtist().name) == null) {
            //404
            throw new EntityNotFoundException("Cette artiste : " + album.getArtist().name + " n'existe pas !");
        }*/
        album = albumRepository.save(album);
        return album;
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}