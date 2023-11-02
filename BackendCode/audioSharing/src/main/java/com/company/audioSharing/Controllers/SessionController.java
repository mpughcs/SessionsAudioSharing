package com.company.audioSharing.Controllers;

import com.company.audioSharing.Models.Session;
import com.company.audioSharing.Repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionRepository repo;
//    create
    @PostMapping("/create-session")
    @ResponseStatus(HttpStatus.CREATED)
    public Session addSession(@RequestBody Session session){
        return repo.save(session);
    }
//    get by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Session getSessionById(@PathVariable int id){
        Optional<Session> returnVal = repo.findById(id);
        return returnVal.orElse(null);
    }
//    get all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Session> getSessions(){
        return repo.findAll();
    }

//    update
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Session session){
        repo.save(session);
    }
//   delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable int id){repo.deleteById(id);}

}
