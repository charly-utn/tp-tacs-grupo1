package org.tptacs.presentation.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tptacs.application.useCases.AddItemToOrderUC;
import org.tptacs.application.useCases.CreateOrderUC;
import org.tptacs.application.useCases.GetItemsFromOrderUC;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderUC createOrderUC;

    @MockBean
    private AddItemToOrderUC addItemToOrderUC;

    @MockBean
    private GetItemsFromOrderUC getItemsFromOrderUC;

    @BeforeEach
    void setUp() {
        Mockito.when(createOrderUC.createOrder(Mockito.any(OrderRequest.class)))
               .thenReturn("123"); 
    }

    @Test
    public void testCreateOrderSuccess() throws Exception {
        OrderRequest request = new OrderRequest(1L,List.of(new ItemOrderRequest("123",1L)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetItemsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/orders/123/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
