package com.shopme.order;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shopme.common.entity.Customer;
import com.shopme.common.entity.order.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>, PagingAndSortingRepository<Order, Integer> {

	@Query("SELECT DISTINCT o FROM Order o JOIN o.orderDetails od JOIN od.product p "
			+ "WHERE o.customer.id = ?2 "
			+ "AND (p.name LIKE %?1% OR CAST(o.status AS string) LIKE %?1%)")
	public Page<Order> findAll(String keyword, Integer customerId, Pageable pageable);
	
	@Query("SELECT o FROM Order o WHERE o.customer.id = ?1")
	public Page<Order> findAll(Integer customerId, Pageable pageable);
	
	public Order findByIdAndCustomer(Integer id, Customer customer);
}
