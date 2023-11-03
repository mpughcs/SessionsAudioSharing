package com.company.audioSharing.Controllers;

import com.company.audioSharing.Models.Song;
import com.company.audioSharing.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongRepository repo;

//    Create a new song
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Song addSong(@RequestBody Song s){
        return repo.save(s);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Song getSongById(@PathVariable int id){
        Optional<Song> returnVal = repo.findById(id);
        return returnVal.orElse(null);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Song> getSongs(){
        return repo.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSong(@RequestBody Song s){
        repo.save(s);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable int id){
        repo.deleteById(id);
    }


}
