package org.tptacs.application.validators;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.tptacs.presentation.requestModels.OrderRequest;

import static br.com.fluentvalidator.predicate.CollectionPredicate.empty;
import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThanOrEqual;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static java.util.function.Predicate.not;

@Component
public class OrderRequestValidator extends Validator<OrderRequest> implements ValidatorHelper {
    @Override
    public void rules() {
        failFastRule();
        ruleFor(OrderRequest::getUserId)
                .must(not(nullValue()))
                    .withMessage(notNullMessage("userId"))
                    .withCode(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .must(greaterThanOrEqual(1L))
                    .withMessage("invalid userId")
                    .critical();

        ruleForEach(OrderRequest::getItems)
                .must(not(nullValue()))
                    .withMessage(notNullMessage("items"))
                    .withCode(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .must(not(empty()))
                    .when(not(nullValue()))
                    .withMessage("items must be not empty")
                    .whenever(not(nullValue()))
                    .withValidator(new ItemOrderRequestValidator())
                    .critical();
    }

    @Override
    public Class<?> getClazz() {
        return OrderRequest.class;
    }
}
