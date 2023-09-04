package org.tptacs.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.GetAnalyticsUC;
import org.tptacs.presentation.responseModels.AnalyticsResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Analytics")
@RequestMapping(value = "/api/analytics", produces = "application/json", consumes = "application/json"  )
public class AnalyticsController {
	
	private final GetAnalyticsUC getAnalyticsUC;
	
	
    public AnalyticsController(GetAnalyticsUC getAnalyticsUC) {
		this.getAnalyticsUC = getAnalyticsUC;
	}

	@GetMapping("/orders")
    public ResponseEntity<AnalyticsResponse> getCountOrders() {
        return ResponseEntity.ok(new AnalyticsResponse(getAnalyticsUC.countOrders()));
    }   
    
    @GetMapping("/users")
    public ResponseEntity<AnalyticsResponse> getUsersUnique() {
        return ResponseEntity.ok(new AnalyticsResponse(getAnalyticsUC.countOrders()));
    }

}
