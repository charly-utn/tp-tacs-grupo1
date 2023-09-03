package org.tptacs.application.validators;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;

import br.com.fluentvalidator.context.ValidationResult;

public class OrderRequestValidatorTest {

    private OrderRequestValidator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new OrderRequestValidator();
    }

    @Test
    public void validateValidOrderRequest() {
        OrderRequest orderRequest = new OrderRequest(1L, List.of(new ItemOrderRequest("item-id", 2L)));

        ValidationResult validationResult = validator.validate(orderRequest);

        assertThat(validationResult.isValid()).isTrue();
    }

    @Test
    public void validateOrderRequestWithInvalidUserId() {
        OrderRequest orderRequest = new OrderRequest(null, List.of(new ItemOrderRequest("item-id", 2L) ));

        ValidationResult validationResult = validator.validate(orderRequest);

        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(2);
        assertThat(validationResult.getErrors().stream().findFirst().get().getCode()).isEqualTo("UNPROCESSABLE_ENTITY");
    }

    @Test
    public void validateOrderRequestWithEmptyItems() {
        OrderRequest orderRequest = new OrderRequest(1L, null);

        ValidationResult validationResult = validator.validate(orderRequest);

        assertThat(validationResult.isValid()).isFalse();
        assertThat(validationResult.getErrors()).hasSize(1);
        assertThat(validationResult.getErrors().stream().findFirst().get().getCode()).isEqualTo("UNPROCESSABLE_ENTITY");
    }

}
