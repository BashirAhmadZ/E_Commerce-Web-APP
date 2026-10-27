
package com.shopme.admin.shippingrate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shopme.admin.product.ProductRepository;
import com.shopme.common.entity.ShippingRate;
import com.shopme.common.entity.product.Product;

@ExtendWith(MockitoExtension.class)
public class ShippingRateServiceTests {

    @Mock
    private ShippingRateRepository shipRepo;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ShippingRateService shipService;

    @Test
    public void testCalculateShippingCost_NoRateFound() {
        Integer productId = 1;
        Integer countryId = 234;
        String state = "ABCDE";

        Mockito.when(
            shipRepo.findByCountryAndState(countryId, state)
        ).thenReturn(null);

        assertThrows(
            ShippingRateNotFoundException.class,
            () -> shipService.calculateShippingCost(
                productId, countryId, state
            )
        );
    }

    @Test
    public void testCalculateShippingCost_RateFound()
            throws ShippingRateNotFoundException {

        Integer productId = 1;
        Integer countryId = 234;
        String state = "New York";

        ShippingRate shippingRate = new ShippingRate();
        shippingRate.setRate(10);

        Mockito.when(
            shipRepo.findByCountryAndState(countryId, state)
        ).thenReturn(shippingRate);

        Product product = new Product();
        product.setWeight(5);
        product.setWidth(4);
        product.setHeight(3);
        product.setLength(8);

        Mockito.when(
            productRepo.findById(productId)
        ).thenReturn(Optional.of(product));

        float shippingCost = shipService.calculateShippingCost(
            productId, countryId, state
        );

        assertEquals(50.0f, shippingCost, 0.001f);
    }
}
