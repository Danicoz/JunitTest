package com.cattsoft.abtractfactory.service;

public class FactoryProducer {

	public static AbstractFactory getFactory(Class clazz){
		
		AbstractFactory obj = null;
		
		try {
			obj = (AbstractFactory) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
}
