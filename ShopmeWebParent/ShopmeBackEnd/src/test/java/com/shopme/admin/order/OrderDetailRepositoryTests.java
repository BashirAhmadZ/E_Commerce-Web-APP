package com.shopme.admin.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.expression.ParseException;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.order.OrderDetail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class OrderDetailRepositoryTests {

	@Autowired private OrderDetailRepository repo;
	
	@Test
	public void testFindWithCategoryAndTimeBetween() throws ParseException {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime startTime = LocalDateTime.parse("2021-08-01", dateTimeFormatter);
		LocalDateTime endTime = LocalDate.parse("2021-09-30", dateTimeFormatter).atStartOfDay();
		
		
		List<OrderDetail> listOrderDetails = repo.findWithCategoryAndTimeBetween(startTime, endTime);
		
		assertThat(listOrderDetails.size()).isGreaterThan(0);
		
		for (OrderDetail detail : listOrderDetails) {
			System.out.printf("%-30s | %d | %10.2f| %10.2f | %10.2f \n", 
					detail.getProduct().getCategory().getName(),
					detail.getQuantity(), detail.getProductCost(),
					detail.getShippingCost(), detail.getSubtotal());
		}
	}
	
	@Test
	public void testFindWithProductAndTimeBetween() throws ParseException {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime startTime = LocalDateTime.parse("2021-10-01", dateTimeFormatter);
		LocalDateTime endTime = LocalDate.parse("2021-10-30", dateTimeFormatter).atStartOfDay();
		
		
		List<OrderDetail> listOrderDetails = repo.findWithProductAndTimeBetween(startTime, endTime);
		
		assertThat(listOrderDetails.size()).isGreaterThan(0);
		
		for (OrderDetail detail : listOrderDetails) {
			System.out.printf("%-70s | %d | %10.2f| %10.2f | %10.2f \n", 
					detail.getProduct().getShortName(),
					detail.getQuantity(), detail.getProductCost(),
					detail.getShippingCost(), detail.getSubtotal());
		}
	}
	
}
