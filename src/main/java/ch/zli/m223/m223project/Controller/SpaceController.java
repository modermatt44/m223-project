package ch.zli.m223.m223project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import ch.zli.m223.m223project.Model.Space;
import ch.zli.m223.m223project.Repository.SpaceRepository;

@RestController
public class SpaceController {

    private final SpaceRepository spaceRepo;

    public SpaceController(SpaceRepository spaceRepo) {
        this.spaceRepo = spaceRepo;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/spaces", name = "getAllSpaces")
    @ResponseStatus(code = HttpStatus.OK, reason = "Spaces found!")
    public List<Space> getSpaces() {
        try{
            return spaceRepo.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Users found!");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/space", consumes = "application/json", produces = "application/json", name = "createSpace")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Space created successfully!")
    public Space createSpace(@RequestBody Space space) {
        try{
            return spaceRepo.save(space);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "EXPECTATION FAILED(CODE 417)\n");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/space/{id}", consumes = "application/json", produces = "application/json", name = "updateSpaceById")
    @ResponseStatus(code = HttpStatus.OK, reason = "Space updated successfully!")
    public Optional<Space> updateSpace(@RequestBody Space space, @PathVariable Long id) {
        Optional<Space> spaceData = spaceRepo.findById(id);
        if (spaceData.isPresent()) {
            Space _space = spaceData.get();
            _space.setName(space.getName());
            spaceRepo.save(_space);
            return spaceRepo.findById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No space found with id: " + id + " to update!");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/space/{id}", name = "deleteSpaceById")
    @ResponseStatus(code = HttpStatus.OK, reason = "Space deleted successfully!")
    public String deleteSpace(@PathVariable Long id) {
        if (spaceRepo.findById(id).isPresent()) {
            spaceRepo.deleteById(id);
            return "Deleted space with id: " + id;
        } 
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No space found with id: " + id + " to delete!");
    }
}
