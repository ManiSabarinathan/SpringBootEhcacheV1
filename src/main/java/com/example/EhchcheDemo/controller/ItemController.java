package com.example.EhchcheDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.EhchcheDemo.cache.ItemCache;
import com.example.EhchcheDemo.model.Item;

@RestController
public class ItemController {
		@Autowired
	    ItemCache itemCache;
	 
	    @GetMapping("/item/{itemId}")
	    @ResponseBody
	    public ResponseEntity<Item> getItem(@PathVariable int itemId) throws Exception{
	    	ResponseEntity responseEntity=null;
	    	System.out.println("Fetching item for : " + itemId);
	        Item item = itemCache.getItem(itemId);
	        if(null != item) 
	        {
	        	responseEntity =  new ResponseEntity<Item>(item, HttpStatus.OK);
	        }else  {
	        	responseEntity = new ResponseEntity("No Matching data in DB", HttpStatus.OK);
	        }
	        return responseEntity;
	    }

	    @PutMapping("/updateItem")
	    @ResponseBody
	    public ResponseEntity<Item> updateItem(@RequestBody Item item){
	    	
	        if(item != null){
	        	itemCache.updateItem(item);
	        }
	        return new ResponseEntity<Item>(item, HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{id}")
	    @ResponseBody
	    public ResponseEntity<Void> deleteItem(@PathVariable int id){
	        itemCache.deleteItem(id);
	        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	    }
}
