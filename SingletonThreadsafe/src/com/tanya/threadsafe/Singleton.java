package com.tanya.threadsafe;


public class Singleton {

	
	private Singleton() {
	}
		
		private volatile Singleton instance;
		
		public Singleton getInstance(){
			if(instance == null){
				synchronized(this){
					if(instance == null){
						instance = new Singleton();
					}
				}
			}
			return instance;
		}
}
