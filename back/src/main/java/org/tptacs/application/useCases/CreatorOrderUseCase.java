package org.tptacs.application.useCases;

import org.tptacs.domain.entities.Order;
import org.tptacs.presentation.requestModels.OrderRequest;

public interface CreatorOrderUseCase {
	Order createOrder(OrderRequest orderRequest);
}
