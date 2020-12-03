package com.java.eval.web.controller;

import com.java.eval.web.model.Album;
import com.java.eval.web.gestion.AlbumGestion;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumGestion albumGestion;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Album addAlbum(@RequestBody Album album){
        album = albumGestion.addAlbum(album);
        return album;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) { albumGestion.deleteAlbum(id); }

}
