package com.htttql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htttql.entity.TaxStatistic;

public interface TaxStatisticRepository extends JpaRepository<TaxStatistic, Integer>{

	public List<TaxStatistic> findOneById(Integer id);

}
