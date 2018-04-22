package com.nguyenvanai.app.managers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.nguyenvanai.app.models.AbstractEntity;;

public abstract class AbstractManager {

	private Map<String, AbstractEntity> map;

	protected AbstractManager() {
		map = new HashMap<String, AbstractEntity>();

	}

	public boolean clear() {
		map.clear();
		return map.size() == 0;
	}

	// get a list of entities
	public List<AbstractEntity> all() {
		return new ArrayList<>(map.values());
	}
	
	

	// get an entity by ID
	public AbstractEntity get(String id) {
		if (isExisted(id)) {
			return map.get(id);
		}
		return null;
	}

	// get Map
	protected Map<String, AbstractEntity> getMap() {
		return map;
	}

	// check existing entity in map
	public boolean isExisted(String id) {
		return map.containsKey(id);
	}

	// check count of entities in map
	public int count() {
		return map.size();
	}

	// add an entity to map
	public boolean add(AbstractEntity item) {

		if (isExisted(item.getId())) {
			return false;
		} else {
			if (validateID(item.getId())) {
				map.put(item.getId(), item);
				return true;
			}
		}
		return false;
	}
	
	// delete a item in map
	public boolean delete(String id) {
		if (isExisted(id)) {
			getMap().remove(id);
			
			return true;
		}
		return false;
	}

	// update a item in map
	public boolean update(AbstractEntity item) {
		if (isExisted(item.getId())) {
			getMap().replace(item.getId(), item);
			return true;
		}
		return false;
	}

	// save data to json file
	public boolean save() {
		Gson gson = new Gson();
		String json = gson.toJson(map.values());
		return FileManager.getInstance().saveJson(json, FileManager.FILE_PATH + getFileName());
	}

	// load data from json file
	public boolean loadData() throws Exception {
		Gson gson = new Gson();
		String json = FileManager.getInstance().loadJson(FileManager.FILE_PATH + getFileName());
		Collection<AbstractEntity> list = gson.fromJson(json, getListType());
		map.clear();
		list.forEach(item -> {
			map.put(item.getId(), item);
		});
		return list != null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		map.values().forEach(item -> {
			builder.append(item.toString());
			builder.append("\n");
		});
		return builder.toString();
	}
	
	public List<AbstractEntity> searchByName(String text){
		text = text.trim().toLowerCase();
		if(text.equals("")){
			return all();
		}
		List<AbstractEntity> list = new ArrayList<>();
		for(AbstractEntity ae : all()) {
			if(ae.getName().toLowerCase().contains(text))
				list.add(ae);
		}
		return list; 
	}
	
	// sort data by Id
	public List<AbstractEntity> sortByID() {
		TreeMap<String,AbstractEntity> sorted = new TreeMap<>(map);
		return  new ArrayList<>(sorted.values());
	}
	
	// sort data by Name
	public List<AbstractEntity> sortByName() {
		TreeMap<String,AbstractEntity> sorted = new TreeMap<String,AbstractEntity>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return map.get(o1).getName().compareTo(map.get(o2).getName());
			}
		});
		sorted.putAll(map);
		return  new ArrayList<>(sorted.values());
	}

	// get json file name
		public abstract String getFileName();

		// get type of collection<?>
		abstract Type getListType();

		// validate id of entity
		abstract boolean validateID(String id);
	
}
