package org.tptacs.presentation.requestModels;

import lombok.Getter;
import java.util.List;

@Getter
public class OrderRequest {
    private Long userId;
    private List<ItemOrderRequest> items;
}
