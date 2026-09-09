package com.jashan.queue_forge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jashan.queue_forge.service.WorkshopService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController 
@RequestMapping ("/workshop")
public class WorkshopController {

    private final WorkshopService workshopService;

    WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping("/start")
    public String startWorkshop() {
        
        workshopService.startWorkshop();
        
        return "WorkShop Started";
    }
    
    
}
