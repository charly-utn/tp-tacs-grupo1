package org.tptacs.application.usesCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tptacs.application.useCases.UpdateOrderUC;
import org.tptacs.domain.entities.Item;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

public class UpdateOrderUCTest {

    private UpdateOrderUC updateOrderUC;
    private Order orderDB;

    @Mock
    private IOrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateOrderUC = new UpdateOrderUC(orderRepository);
        orderDB = new Order("order123", 1L, List.of(new ItemOrder(new Item("abc","name",new BigDecimal(100)),1L)),OrderStatus.NEW);

    }

    @Test
    public void testUpdateStatusOrderWithValidData() {

        when(orderRepository.get("order123")).thenReturn(orderDB);

        Boolean result = updateOrderUC.updateStatusOrder("order123", 1L, OrderStatus.CLOSED);

        assertThat(result).isTrue();
        assertThat(orderDB.getStatus()).isEqualTo(OrderStatus.CLOSED);

        verify(orderRepository, times(1)).save(orderDB);
    }

    @Test
    public void testUpdateStatusOrderWithInvalidData() {

    	orderDB.setId("order1235");
    	
        when(orderRepository.get("order1235")).thenReturn(orderDB);

        Boolean result = updateOrderUC.updateStatusOrder("order1235", 2L, OrderStatus.CLOSED);

        assertThat(result).isFalse();
        assertThat(orderDB.getStatus()).isEqualTo(OrderStatus.NEW); // Estado no se ha actualizado

        verify(orderRepository, never()).save(orderDB);
    }

    @Test
    public void testUpdateStatusOrderWithNonExistingOrder() {
        when(orderRepository.get("order123")).thenReturn(null);

        Boolean result = updateOrderUC.updateStatusOrder("order123", 1L, OrderStatus.CLOSED);

        assertThat(result).isFalse();

        verify(orderRepository, never()).save(any(Order.class));
    }
}
