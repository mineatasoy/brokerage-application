package org.broker.repository;

import org.broker.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, String> {

    ArrayList<Asset> findByCustomerId(String customerId);

    Asset findByCustomerIdAndAssetName(String customerId, String assetName);


}
