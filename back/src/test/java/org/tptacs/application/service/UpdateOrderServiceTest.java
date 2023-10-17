package org.tptacs.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.UserRepository;

public class UpdateOrderServiceTest {

	

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private OrderRepository pedido;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

//    @Test
//    @Disabled
//    public void testUpdateStatusOrderWithValidData() {
//    	Order orderDB = new Order("order123", "1", "", List.of(new ItemOrder(new Item("abc","name",new BigDecimal(100), ""),1L)),OrderStatus.NEW);
//
//        when(orderRepository.get("order123")).thenReturn(orderDB);
//
//        updateOrderUC.updateStatusOrder("order123", "1", OrderStatus.CLOSED);
//
//        assertThat(orderDB.getStatus()).isEqualTo(OrderStatus.CLOSED);
//
//        verify(orderRepository, times(1)).save(orderDB);
//    }
//
//    @Test
//    @Disabled
//    public void testUpdateStatusOrderWithInvalidData() {
//    	OrderOld orderDB = new OrderOld("order1235", "1", "", List.of(new ItemOrderOld(new ItemOld("abc","name",new BigDecimal(100), ""),1L)),OrderStatus.NEW);
//    	
//        when(orderRepository.get("order1235")).thenReturn(orderDB);
//
//        updateOrderUC.updateStatusOrder("order1235", "2", OrderStatus.CLOSED);
//
//        assertThat(orderDB.getStatus()).isEqualTo(OrderStatus.NEW); // Estado no se ha actualizado
//
//        verify(orderRepository, never()).save(orderDB);
//    }
//
//    @Test
//    @Disabled
//    public void testUpdateStatusOrderWithNonExistingOrder() {
//    	OrderOld orderDB = new OrderOld("order123", "1", "", List.of(new ItemOrderOld(new ItemOld("abc","name",new BigDecimal(100), ""),1L)),OrderStatus.NEW);
//    
//        when(orderRepository.get("order123")).thenReturn(null);
//
//        updateOrderUC.updateStatusOrder("order123", "1", OrderStatus.CLOSED);
//        
//        assertThat(orderDB.getStatus()).isEqualTo(OrderStatus.NEW); // Estado no se ha actualizado
//
//        verify(orderRepository, never()).save(any(OrderOld.class));
//    }
}
