package com.agrifood.nephele.farmersapi.service.impl;

import com.agrifood.nephele.farmersapi.model.Farmer;
import com.agrifood.nephele.farmersapi.repository.FarmerRepository;
import com.agrifood.nephele.farmersapi.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nikos on 4/7/2019.
 */

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public Iterable<Farmer> getFarmers() {
        return farmerRepository.findAll();
    }

    @Override
    public Optional<Farmer> getFarmerById(long id) {
        return farmerRepository.findById(id);
    }

    @Override
    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    @Override
    public void deleteFarmer(long id) {
        try {
             farmerRepository.findById(id);
            farmerRepository.deleteById(id);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
