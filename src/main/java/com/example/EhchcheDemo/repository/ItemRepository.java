package com.example.EhchcheDemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.EhchcheDemo.model.Item;

@Repository
public class ItemRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//getItem
	//deleteItem
	//updateItem
	
	public Item getItemById(int id) {
		Item item=null;
		System.out.println("Getting data from Repository through jdbcTemplate...");
		try {
			String query = "select * from ITEM where id=?";
			item =  jdbcTemplate.queryForObject(query, new Object[] {id},new BeanPropertyRowMapper<>(Item.class));	
		}
		catch(Exception ee) {
			System.out.println(ee.getMessage());
		}
		return item;
		
	}
	
	public int deleteItem(int id) {
		System.out.println("Deleting from Repository through jdbcTemplate...");
		String query = "delete from ITEM where id=?";
		int size = jdbcTemplate.update(query, id);
		return size;
	}
	
	
	public int updateItem(Item item) {
		System.out.println("Updating data from Repository through jdbcTemplate...");
		String query = "UPDATE ITEM SET name=?, category=? WHERE id =?";
		int updateSize = jdbcTemplate.update(query, 
							new Object[] { item.getName(), item.getCategory(),Integer.valueOf(item.getId()) });
		System.out.println("updateSize==> " + updateSize);
		return updateSize;
	}
	
	
	/*
	 * 
		CREATE DATABASE ITEMDB;
		
		USE ITEMDB;
		
		
		CREATE TABLE ITEM(
		id INT NOT NULL PRIMARY KEY,
		name VARCHAR(255),
		category VARCHAR(10)
		);
	
	 */
	
	
}
