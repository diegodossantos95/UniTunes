package com.unitunes.model.lista;

import java.util.ArrayList;
import java.util.Iterator;

import com.unitunes.model.Midia;

public class MidiaIterator implements Iterator<Midia> {
	
	private ArrayList<Midia> objects;
	private int cursor = 0;
    
	public MidiaIterator(ListaMidia lista){
		this.objects = lista.getObjects();
	}
	
	@Override
    public boolean hasNext() {
        if (cursor < objects.size())
            return true;
        else
            return false;
    }

	@Override
    public Midia next() {
        if (this.hasNext())
            return objects.get(cursor++);
        else
            return null;
    }
    
    @Override
    public void remove() {
    	objects.remove(cursor);
    }
}