package com.unitunes.model.lista;

import java.util.ArrayList;
import java.util.Iterator;

import com.unitunes.model.Midia;

public class ListaMidia implements Iterable<Midia> {
    private ArrayList<Midia> objects = new ArrayList<Midia>();

    public ListaMidia(ArrayList<Midia> objects){
		this.objects = objects;
	}
    
    protected ArrayList<Midia> getObjects() {
		return objects;
	}
    
    @Override
    public Iterator<Midia> iterator() {
        return new MidiaIterator(this);
    }
}