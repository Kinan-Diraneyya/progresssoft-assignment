package bloomberg.deals.service;

import bloomberg.deals.dto.DealDto;
import bloomberg.deals.model.Deal;

import java.util.List;
import java.util.Optional;

public interface DealsService {

    /**
     * Creates a new deal.
     *
     * @param deal the deal to create
     * @return the created deal
     */
    Deal createDeal(DealDto deal);

    /**
     * Retrieves a deal by its id.
     *
     * @param id the id of the deal to retrieve
     * @return an Optional containing the found deal or an empty Optional if no deal is found
     */
    Optional<Deal> getDealById(String id);

    /**
     * Retrieves all deals.
     *
     * @return a list of all deals
     */
    List<Deal> getAllDeals();

    /**
     * Updates an existing deal.
     *
     * @param deal the deal with updated information
     * @return the updated deal
     */
    Deal updateDeal(Deal deal);

    /**
     * Deletes a deal by its id.
     *
     * @param id the id of the deal to delete
     */
    void deleteDeal(String id);
}
