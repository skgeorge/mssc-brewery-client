package guru.springframework.msscbreweryclient.repositories;

import guru.springframework.msscbreweryclient.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
