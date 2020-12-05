package com.java.eval.web.service;

import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;

import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    public Artist findById(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()) {
            //404
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas");
        }
        return artist.get();
    }

    public List<Artist> findByNameLike(String name){
        List<Artist> artistList = artistRepository.findByNameLike(name);
        return artistList;
    }

    public Page<Artist> listArtists(Integer page, Integer size, String sortProperty, String sortDirection){
        if (page < 0){
            //404
            throw new IllegalArgumentException("La page doit être un entier positif ou nul !");
        }
        if (size <= 0 || size >50){
            //404
            throw new IllegalArgumentException("La taille doit être un entier entre 1 et 50 !");
        }
        if(!"ASC".equalsIgnoreCase(sortDirection) && !"DESC".equalsIgnoreCase((sortDirection))){
            //404
            throw new IllegalArgumentException("Le sens de tri doit être ascendant ou descendant");
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    public Artist addArtist(Artist artist)
    {
        if (artistRepository.findByName(artist.getName()) != null)
        {
            //404
            throw new EntityExistsException("Cet artiste existe déjà ");
        }
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Artist artist) {
        if (artistRepository.findById(artist.getId()).isEmpty())
        {
            //404
            throw new EntityNotFoundException("Cet artiste existe déjà");
        }
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        if (artistRepository.findById(id).isEmpty())
        {
            //404
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas !");
        }
        List<Album> albumList = albumRepository.findByArtistId(id);
        for (Album album : albumList) {
            albumRepository.deleteById(album.getId());
        }
        artistRepository.deleteById(id);

    }
}