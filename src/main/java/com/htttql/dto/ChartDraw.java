package com.htttql.dto;

import java.util.List;

import com.htttql.entity.Product;

import lombok.Data;


@Data
public class ChartDraw {
	private List<String> products;
	private List<Integer> moneys;
	private List<PieChart> pieList;
}
