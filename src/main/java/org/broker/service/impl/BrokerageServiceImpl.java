package org.broker.service.impl;

import org.broker.model.Asset;
import org.broker.model.Order;
import org.broker.model.response.AssetResponse;
import org.broker.model.response.OrderResponse;
import org.broker.repository.AssetRepository;
import org.broker.repository.OrderRepository;
import org.broker.service.BrokerageService;
import org.broker.util.Constants;
import org.broker.util.SideEnum;
import org.broker.util.StatusEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerageServiceImpl implements BrokerageService {

    private final OrderRepository orderRepository;

    private final AssetRepository assetRepository;

    public BrokerageServiceImpl(OrderRepository orderRepository, AssetRepository assetRepository) {
        this.orderRepository = orderRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public OrderResponse createOrder(Order order) {
        OrderResponse response = new OrderResponse();
        //checkAssetUsableSize(order);
        if(order.getOrderSide().equals(SideEnum.SELL.name())){
            orderRepository.save(order);
            response.setOrder(order);
            response.setMessage(Constants.ORDER_CREATED_SUCCESSFULLY);
        }else{ //SIDE: BUY
            boolean customerUsableSizeAvailable = checkCustomerSize(order);
            if(customerUsableSizeAvailable){
                orderRepository.save(order);
                response.setOrder(order);
                response.setMessage(Constants.ORDER_CREATED_SUCCESSFULLY);
            }else{
                response.setOrder(order);
                response.setMessage(Constants.ORDER_CREATE_USABLESIZE_NOT_AVAILABLE);
            }
        }
        return response;
        }

    @Override
    public List<Order> getOrdersByCustomerWithDate(String customerId, LocalDate dateStart, LocalDate dateEnd) {
        return orderRepository.findByCustomerIdAndCreateDateBetween(customerId, dateStart, dateEnd);
    }

    @Override
    public OrderResponse cancelOrder(String orderId) {
        OrderResponse response = new OrderResponse();
        Optional<Order> order = orderRepository.findById(orderId);

        if (order == null) {
            response.setMessage(Constants.ORDER_NOT_FOUND);
            return response;
        }

        if (order.get().getStatus().equals(StatusEnum.PENDING.name())) {
            response.setOrder(order.get());
            try {
                order.get().setStatus(StatusEnum.CANCELED.name());
                orderRepository.save(order.get());
                response.setMessage(Constants.ORDER_DELETED_SUCCESSFULLY);
            } catch (Exception e) {
                response.setMessage(Constants.ORDER_DELETE_ERROR);
            }
        } else {
            response.setOrder(order.get());
            response.setMessage(Constants.ORDER_DELETE_STATUS_UNAVAILABLE);
        }

        return response;

    }

    @Override
    public ArrayList<Asset> getAssetsByCustomer(String customerId) {

        return (ArrayList<Asset>) assetRepository.findByCustomerId(customerId);

    }

    @Override
    public AssetResponse depositMoney(String customerId, double size) {
        AssetResponse response = new AssetResponse();
        Asset asset = assetRepository.findByCustomerIdAndAssetName(customerId, Constants.ASSET_TRY);
        double actualUsableSize = asset.getUsableSize();
        asset.setUsableSize(actualUsableSize + size);
        assetRepository.save(asset);
        response.setAsset(asset);
        return response;
    }

    @Override
    public AssetResponse withdrawMoney(String customerId, double size) {
        AssetResponse response = new AssetResponse();
        Asset asset = assetRepository.findByCustomerIdAndAssetName(customerId, Constants.ASSET_TRY);
        double actualUsableSize = asset.getUsableSize();
        if(actualUsableSize>=size){
            asset.setUsableSize(actualUsableSize - size);
            assetRepository.save(asset);
        }else{
            response.setMessage(Constants.WITHDRAW_MONEY_USABLESIZE_NOT_AVAILABLE);
        }
        response.setAsset(asset);
        return response;
    }

    private boolean checkCustomerSize(Order order) {
        boolean customerSizeAvailable = false;
        double customerTryUsableSize = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), Constants.ASSET_TRY).getUsableSize();
        if (customerTryUsableSize >= (order.getSize() * order.getPrice())) {
            customerSizeAvailable = true;
        }
        return customerSizeAvailable;
    }

}
