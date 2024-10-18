package org.broker.repository;

import org.broker.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByCustomerIdAndCreateDateBetween(String customerId, LocalDate startDate, LocalDate endDate);


}
