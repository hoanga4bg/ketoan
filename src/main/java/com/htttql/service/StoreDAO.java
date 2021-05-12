package com.htttql.service;

import com.htttql.entity.Product;
import com.htttql.entity.Store;

public interface StoreDAO extends GeneralService<Store> {
	public Store findOneByProduct(Product product);

}
