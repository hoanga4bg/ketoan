package com.htttql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htttql.entity.Product;
import com.htttql.entity.Store;
import com.htttql.repository.ProductRepository;
import com.htttql.repository.StoreRepository;
import com.htttql.service.StoreDAO;
@Service
public class StoreDAOImpl implements StoreDAO {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private ProductRepository proRepo;
	@Override
	public List<Store> findAll() {
		List<Product> list=proRepo.findByStatus(0);
		
		List<Store> listStore=new ArrayList<Store>();
		
		for(Product p:list) {
			Store s=findOneByProduct(p);
			listStore.add(s);
		}
		return listStore;
	}

	@Override
	public Store save(Store t) {
		// TODO Auto-generated method stub
		return storeRepository.save(t);
	}

	@Override
	public Store findOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store update(int id, Store t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findOneByProduct(Product product) {
		Store store = new Store();
		store = storeRepository.findOneByProduct(product);
		return store;
	}

}
