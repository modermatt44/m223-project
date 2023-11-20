package ch.zli.m223.m223project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.zli.m223.m223project.Model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}
