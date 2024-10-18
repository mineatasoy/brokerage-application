package org.broker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.broker.model.Asset;
import org.broker.model.Order;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {


    Asset asset;
    String message="";


}
