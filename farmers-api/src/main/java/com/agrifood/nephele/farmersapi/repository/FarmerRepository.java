package com.agrifood.nephele.farmersapi.repository;

import com.agrifood.nephele.farmersapi.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nikos on 4/7/2019.
 */

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
