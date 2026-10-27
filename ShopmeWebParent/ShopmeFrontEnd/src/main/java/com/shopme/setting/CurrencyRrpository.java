package com.shopme.setting;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shopme.common.entity.Currency;
import com.shopme.common.entity.setting.Setting;

public interface CurrencyRrpository extends CrudRepository<Currency, Integer> {
	
	@Query("SELECT s FROM Setting s WHERE s.category = 'CURRENCY'")
	public List<Setting> findAllCurrencySettings();

}
