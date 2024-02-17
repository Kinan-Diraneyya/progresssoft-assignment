package bloomberg.deals.repository;

import bloomberg.deals.model.Deal;
import org.springframework.data.repository.CrudRepository;

public interface DealsRepository extends CrudRepository<Deal, String> {
}
