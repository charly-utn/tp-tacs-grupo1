package org.tptacs.presentation.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tptacs.application.useCases.UpdateOrderUC;
import org.tptacs.domain.enums.OrderStatus;

@WebMvcTest(UpdateOrderController.class)
public class UpdateOrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateOrderUC updateOrderUC;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateOrderSuccess() throws Exception {
        String orderId = "order123";
        Long userId = 1L;
        when(updateOrderUC.updateStatusOrder(orderId, userId, OrderStatus.CLOSED)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/orders/{orderId}/close/{userId}", orderId, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}
