
package com.java.eval.web.controller;

import com.java.eval.web.service.ArtistService;
import com.java.eval.web.model.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Artist findById(@PathVariable(value = "id") Long id) {
        return artistService.findById(id);
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "name")
    public List<Artist> findByName(@RequestParam("name") String name) {
        return artistService.findByNameLike(name);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> listArtist(@RequestParam("") Integer page,
                                   @RequestParam("") Integer size,
                                   @RequestParam("") String sortProperty,
                                   @RequestParam("") String sortDirection) {
        return artistService.listArtists(page, size, sortProperty, sortDirection);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist addArtist(@RequestBody Artist artist) {
        return artistService.addArtist(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return artistService.updateArtist(artist);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
    }
}


