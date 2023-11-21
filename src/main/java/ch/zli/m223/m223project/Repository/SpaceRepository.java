package ch.zli.m223.m223project.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ch.zli.m223.m223project.Model.Space;

public interface SpaceRepository extends CrudRepository<Space, Long>{
    List<Space> findAll();
}
