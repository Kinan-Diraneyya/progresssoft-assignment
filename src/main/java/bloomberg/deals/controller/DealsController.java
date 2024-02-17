package bloomberg.deals.controller;

import bloomberg.deals.dto.DealDto;
import bloomberg.deals.model.Deal;
import bloomberg.deals.service.DealsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The DealsController class handles REST API requests for dealing with deals (see {@link Deal}).
 */
@RestController
@RequestMapping("/api/deals")
@Validated
public class DealsController {

    private final DealsService dealsService;

    /**
     * Constructs a DealsController
     * @param dealsService The service for dealing with deals operations.
     */
    @Autowired
    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }

    /**
     * Creates a new deal
     * @param dealDto The deal DTO containing the data for the new deal.
     * @return A ResponseEntity containing the created deal and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody @Valid DealDto dealDto) {
        Deal createdDeal = dealsService.createDeal(dealDto);
        return new ResponseEntity<Deal>(createdDeal, HttpStatus.CREATED);
    }

    /**
     * Retrieves a deal by ID.
     * @param id The ID of the deal to retrieve.
     * @return A ResponseEntity containing the found deal and HTTP status OK,
     * or HTTP status NOT FOUND if no deal with the given ID exists.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable String id) {
        Optional<Deal> deal = dealsService.getDealById(id);
        return deal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves all available deals.
     * @return A ResponseEntity containing the list of all deals and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealsService.getAllDeals();
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    /**
     * Updates an existing deal with the provided deal information.
     * @param id The ID of the deal to update.
     * @param deal The updated deal information.
     * @return A ResponseEntity containing the updated deal and HTTP status OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable String id, @RequestBody @Valid Deal deal) {
        Deal updatedDeal = dealsService.updateDeal(deal);
        return new ResponseEntity<>(updatedDeal, HttpStatus.OK);
    }

    /**
     * Deletes a deal by its ID.
     * @param id The ID of the deal to delete.
     * @return A ResponseEntity with HTTP status NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable String id) {
        dealsService.deleteDeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
