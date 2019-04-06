package com.agrifood.nephele.farmersapi.service;

import com.agrifood.nephele.farmersapi.model.Farmer;

import java.util.Optional;

/**
 * Created by nikos on 4/7/2019.
 */
public interface FarmerService {
    public Iterable<Farmer> getFarmers();
    public Optional<Farmer> getFarmerById(long id);
    public Farmer saveFarmer(Farmer farmer);
    public void deleteFarmer(long id);
}
