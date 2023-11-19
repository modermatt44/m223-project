package ch.zli.m223.m223project.Repository;

import org.springframework.data.repository.CrudRepository;

import ch.zli.m223.m223project.Model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}
