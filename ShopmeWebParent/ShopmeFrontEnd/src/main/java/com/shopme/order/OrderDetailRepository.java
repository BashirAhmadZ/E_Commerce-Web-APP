package com.shopme.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shopme.common.entity.order.OrderDetail;
import com.shopme.common.entity.order.OrderStatus;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer>, PagingAndSortingRepository<OrderDetail, Integer> {

	@Query("SELECT COUNT(d) FROM OrderDetail d JOIN OrderTrack t ON d.order.id = t.order.id"
			+ " WHERE d.product.id = ?1 AND d.order.customer.id = ?2 AND"
			+ " t.status = ?3")
	public Long countByProductAndCustomerAndOrderStatus(
			Integer productId, Integer customerId, OrderStatus status);
}
