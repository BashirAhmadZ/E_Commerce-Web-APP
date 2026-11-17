package com.shopme.section;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shopme.common.entity.section.Section;

public interface SectionRepository extends CrudRepository<Section, Integer>, PagingAndSortingRepository<Section, Integer> {

	// list sections by enabled status and sorted by order in ascending order
		public List<Section> findAllByEnabledOrderBySectionOrderAsc(boolean enabled);
}
