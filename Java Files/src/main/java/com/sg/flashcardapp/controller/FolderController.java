/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flashcardapp.controller;

import com.sg.flashcardapp.model.Folder;
import com.sg.flashcardapp.service.FlashCardService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author annamaxam
 */
@RestController
public class FolderController {

    @Autowired
//    private FolderRepository folders;
    private FlashCardService service;

    @GetMapping(value = "/folder/{id}")
    public Folder getFolder(@PathVariable("id") int id) {
        return service.getFolder(id);
    }

    @PostMapping(value = "/folder/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Folder createFolder(@PathVariable("id") int id, @Valid @RequestBody Folder folder) {
        folder.setUserId(id);
        return service.createFolder(folder);
    }
    
    @PostMapping(value = "/folder")
    @ResponseStatus(HttpStatus.CREATED)
    public Folder createFolder(@Valid @RequestBody Folder folder) {
        return service.createFolder(folder);
    }

    @DeleteMapping(value = "/folder/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFolder(@PathVariable("id") int id) {
        service.deleteFolder(id);
    }

    @PutMapping(value = "/folder/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Folder updateFolder(@PathVariable("id") int id, @Valid @RequestBody Folder folder) throws UpdateIntegrityException {
        // favor the path variable over the id in the object if they differ
        if (id != folder.getFolderId()) {
            throw new UpdateIntegrityException("Folder Id on URL must match Folder Id in submitted data.");
        }

        return service.updateFolder(id, folder);
    }

    @GetMapping(value = "/folders")
    public List<Folder> getAllFolders() {
        return service.getAllFolders();
    }

    @GetMapping(value = "/folders/user")
    public List<Folder> getAllFoldersByUserId(Principal principal) {
        int userId = service.getUserId(principal.getName());
        return service.getAllFoldersByUserId(userId);
    }
}
