package com.example.EhchcheDemo.cache;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.EhchcheDemo.model.Item;
import com.example.EhchcheDemo.repository.ItemRepository;

@Component
public class ItemCache {
	
	@Autowired
    ItemRepository itemRepo;
	
	@Cacheable(value="dateCache", key="#date")
	public Date getDate() {
		return new Date();
	}
	
	@Cacheable(value="itemCache", key="#id") // Result of the invoking method can be cached.
    public Item getItem(int id) throws Exception{
        System.out.println("In ItemCache Component[getItem()]..");
        return itemRepo.getItemById(id);
    }

    //@CacheEvict(value="addresses", allEntries=true)
	@CacheEvict(value="itemCache",key = "#id")	
	public int deleteItem(int id){
        System.out.println("In ItemCache Component[deleteItem()]...");
        return itemRepo.deleteItem(id);
    }

    @CachePut(value="itemCache")
    public int updateItem(Item item){
        System.out.println("In ItemCache Component[updateItem()]..");
        return itemRepo.updateItem(item);
    }
}
