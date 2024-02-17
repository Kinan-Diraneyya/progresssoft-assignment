package bloomberg.deals.service;

import bloomberg.deals.dto.DealDto;
import bloomberg.deals.model.Deal;
import bloomberg.deals.repository.DealsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DealsServiceImplTest {

    @Mock
    private DealsRepository dealsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DealsServiceImpl dealsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDeal_Success() {
        Deal deal = new Deal("1", "USD", "EUR", LocalDateTime.now(), 100.0);
        when(dealsRepository.save(any(Deal.class))).thenReturn(deal);
        when(modelMapper.map(any(DealDto.class), eq(Deal.class))).thenReturn(deal);

        Deal createdDeal = dealsService.createDeal(new DealDto("1", "USD", "EUR", 100.0));

        assertNotNull(createdDeal);
        assertEquals("1", createdDeal.getId());
        verify(dealsRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void getDealById_Found() {
        String dealId = "1";
        Optional<Deal> expectedDeal = Optional.of(new Deal(dealId, "USD", "EUR", LocalDateTime.now(), 100.0));
        when(dealsRepository.findById(dealId)).thenReturn(expectedDeal);

        Optional<Deal> actualDeal = dealsService.getDealById(dealId);

        assertTrue(actualDeal.isPresent());
        assertEquals(dealId, actualDeal.get().getId());
        verify(dealsRepository, times(1)).findById(dealId);
    }

    @Test
    void updateDeal_Exists() {
        Deal existingDeal = new Deal("1", "USD", "EUR", LocalDateTime.now(), 100.0);
        when(dealsRepository.existsById(existingDeal.getId())).thenReturn(true);
        when(dealsRepository.save(any(Deal.class))).thenReturn(existingDeal);

        Deal updatedDeal = dealsService.updateDeal(existingDeal);

        assertNotNull(updatedDeal);
        verify(dealsRepository, times(1)).save(existingDeal);
        verify(dealsRepository, times(1)).existsById(existingDeal.getId());
    }

    @Test
    void deleteDeal_Success() {
        String dealId = "1";

        doNothing().when(dealsRepository).deleteById(dealId);

        dealsService.deleteDeal(dealId);

        verify(dealsRepository, times(1)).deleteById(dealId);
    }
}
