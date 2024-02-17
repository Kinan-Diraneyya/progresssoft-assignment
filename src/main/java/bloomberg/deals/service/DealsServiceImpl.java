package bloomberg.deals.service;

import bloomberg.deals.model.Deal;
import bloomberg.deals.repository.DealsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DealsServiceImpl implements DealsService {

    private final DealsRepository dealsRepository;

    @Override
    public Deal createDeal(Deal deal) {
        log.info("Creating deal: {}", deal);
        Deal savedDeal = dealsRepository.save(deal);
        log.info("Created deal with ID: {}", savedDeal.getId());
        return savedDeal;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Deal> getDealById(String id) {
        log.debug("Fetching deal by ID: {}", id);
        return dealsRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deal> getAllDeals() {
        log.debug("Fetching all deals");
        List<Deal> deals = new ArrayList<>();
        dealsRepository.findAll().forEach(deals::add);
        log.debug("Found {} deals", deals.size());

        return deals;
    }

    @Override
    public Deal updateDeal(Deal deal) {
        log.info("Updating deal with ID: {}", deal.getId());
        if (deal.getId() == null || !dealsRepository.existsById(deal.getId())) {
            log.error("Attempted to update a deal that does not exist with ID: {}", deal.getId());
            throw new IllegalArgumentException("Deal with ID " + deal.getId() + " does not exist.");
        }
        Deal updatedDeal = dealsRepository.save(deal);
        log.info("Updated deal with ID: {}", updatedDeal.getId());
        return updatedDeal;
    }

    @Override
    public void deleteDeal(String id) {
        log.info("Deleting deal with ID: {}", id);
        dealsRepository.deleteById(id);
        log.info("Deleted deal with ID: {}", id);

    }
}
