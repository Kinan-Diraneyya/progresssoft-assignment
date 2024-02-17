package bloomberg.deals.controller;

import bloomberg.deals.dto.DealDto;
import bloomberg.deals.model.Deal;
import bloomberg.deals.service.DealsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DealsController.class)
public class DealsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealsService dealsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createDeal_Success() throws Exception {
        DealDto dealDto = new DealDto("1", "USD", "JOD", 5);
        Deal deal = new Deal("1", "USD", "JOD", LocalDateTime.now(), 5);

        given(dealsService.createDeal(any(DealDto.class))).willReturn(deal);

        mockMvc.perform(post("/api/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dealDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(deal.getId()));
    }

    @Test
    void getDealById_Found() throws Exception {
        String dealId = "1";
        Optional<Deal> deal = Optional.of(new Deal("1", "USD", "JOD", LocalDateTime.now(), 5));

        given(dealsService.getDealById(dealId)).willReturn(deal);

        mockMvc.perform(get("/api/deals/{id}", dealId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dealId));
    }

    @Test
    void getAllDeals_Success() throws Exception {
        Deal deal1 = new Deal("1", "USD", "JOD", LocalDateTime.now(), 5);
        Deal deal2 = new Deal("2", "EUR", "JOD", LocalDateTime.now(), 2);

        given(dealsService.getAllDeals()).willReturn(Arrays.asList(deal1, deal2));

        mockMvc.perform(get("/api/deals")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateDeal_Success() throws Exception {
        Deal updatedDeal = new Deal("1", "USD", "JOD", LocalDateTime.now(), 5);

        given(dealsService.updateDeal(any(Deal.class))).willReturn(updatedDeal);

        mockMvc.perform(put("/api/deals/{id}", updatedDeal.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDeal)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDeal_Success() throws Exception {
        String dealId = "1";

        mockMvc.perform(delete("/api/deals/{id}", dealId))
                .andExpect(status().isNoContent());
    }
}
