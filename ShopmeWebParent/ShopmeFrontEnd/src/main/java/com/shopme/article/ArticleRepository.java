package com.shopme.article;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shopme.common.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer>, PagingAndSortingRepository<Article, Integer> {

	@Query("SELECT a FROM Article a WHERE a.alias = ?1")
	public Article findByAlias(String alias);
}
