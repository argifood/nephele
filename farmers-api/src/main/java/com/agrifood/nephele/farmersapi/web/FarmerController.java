package com.agrifood.nephele.farmersapi.web;

import com.agrifood.nephele.farmersapi.model.Farmer;
import com.agrifood.nephele.farmersapi.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nikos on 4/7/2019.
 */

@RestController
@RequestMapping("/api")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping("/farmers")
    public Iterable<Farmer> getFarmers() {
        return farmerService.getFarmers();
    }
}
