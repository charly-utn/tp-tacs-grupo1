package org.tptacs.presentation.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tptacs.application.useCases.AddItemToOrderUseCase;
import org.tptacs.application.useCases.CreatorOrderUseCase;
import org.tptacs.application.useCases.GetItemFromOrderUseCase;
import org.tptacs.application.useCases.RemoveItemFromOrderUseCase;
import org.tptacs.application.useCases.UpdateItemOrderUseCase;
import org.tptacs.application.useCases.UpdateOrderUseCase;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreatorOrderUseCase createOrderUC;

    @MockBean
    private AddItemToOrderUseCase addItemToOrderUC;

    @MockBean
    private GetItemFromOrderUseCase getItemsFromOrderUC;
    
    @MockBean
    private UpdateOrderUseCase updateOrderUC;
    
    @MockBean
    private UpdateItemOrderUseCase updateItemOrderUC;
    
    @MockBean
    private RemoveItemFromOrderUseCase removeItemFromOrderUC;
    
    @MockBean
    private BaseController baseController;

    @BeforeEach
    void setUp() {
        Mockito.when(createOrderUC.createOrder(Mockito.any(OrderRequest.class)))
        	.thenReturn(new Order("123","1","OrderName",List.of(),OrderStatus.NEW)); 
        Mockito.when(baseController.getUserFromJwt())
        	.thenReturn(new User("1","userTest","email@email.com","UnPasswordMuySeguro1234")); 
    }

    @Test
    public void testCreateOrderSuccess() throws Exception {
        OrderRequest request = new OrderRequest(List.of(new ItemOrderRequest("123",1L)));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(403));
    }

    @Test
    public void testGetItemsSuccessNotAhutorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/orders/123/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401));
    }
    
    @Test
    public void testUpdateOrderFail() throws Exception {
        String orderId = "order123";
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/orders/{orderId}/close/{userId}", orderId, userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }


}
