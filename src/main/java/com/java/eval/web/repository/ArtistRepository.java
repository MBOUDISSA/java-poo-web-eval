package com.java.eval.web.repository;

import com.java.eval.web.model.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;



public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT * FROM artist WHERE artist.Name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Artist> findByNameLike(@Param("name")String name);

    Artist findByName(String name);

}
