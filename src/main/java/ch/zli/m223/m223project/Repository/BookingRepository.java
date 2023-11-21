package ch.zli.m223.m223project.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.zli.m223.m223project.Model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();
}
