package com.ordinov.ersp.tools;

import com.google.gson.GsonBuilder;

public class BootstrapTableDataUtils {

	public static String toJson(Object rows,long total){
		BSTableObject<?> object = getObject(rows,total);
		return new GsonBuilder().create().toJson(object);
	}
	
	@SuppressWarnings("rawtypes")
	public static BSTableObject<?> getObject(Object rows,long total){
		BSTableObject<?> object = new BSTableObject();
		object.setRows(rows);
		object.setTotal(total);
		return object;
	}
}
