/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import model.base.GenericSet;

/**
 *
 * @author Student
 * 
 * For educational purpose not handling integer overflow!
 * 
 */
public class ArraySet<T> extends GenericSet<T> {
    
    private T[] arr;
    private int head;
    
    public ArraySet(){
        this(30);
    }
    public ArraySet(int initialSize){
        if(initialSize < 0){
            initialSize*=-1;
        } else if(initialSize==0){
            initialSize=30;
        }
        head--;
        arr = (T[]) new Object[initialSize];
    }
    
    @Override
    public T add(T t) {
        Objects.requireNonNull(t);
        if(head+1>=arr.length){
            ensureArr((int)(arr.length*1.5)); // Integer overflow will occurs.
        }
        arr[++head] = t;
        modCount++;
        return t;
    }

    @Override
    public boolean delete(T t) {
        Objects.requireNonNull(t);
        for(Iterator<T>itr=iterator(); itr.hasNext();){
            if(t.equals(itr.next())){
                itr.remove();
                Arrays.sort(arr, new NullLast());
                head--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public int find(T t) {
        int index = 0;
        for(Iterator<T>itr=iterator(); itr.hasNext(); index++){
            if(t.equals(itr.next()))
                return index;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            
            private int index = -1;
            private int expectedModCount = modCount;
            
            private boolean calledNext = false;
            
            @Override
            public boolean hasNext() {
                return index < head;
            }
            @Override
            public T next() {
                calledNext = true;
                try{
                  if(expectedModCount!=modCount)
                      throw new ConcurrentModificationException();
                  return arr[++index];  
                } catch(ArrayIndexOutOfBoundsException err){
                    throw new NoSuchElementException(err.getMessage());
                } 
            }     
            @Override
            public void remove(){
                if(!calledNext){
                    throw new IllegalStateException();
                } 
                arr[index] = null;
                calledNext = false;
            }
        };
    }
    
    // Integer overflow will occurs.
    private void ensureArr(int targetSize){
        if(targetSize==1){
            targetSize = 10;
        }
        arr = Arrays.copyOf(arr, targetSize);
    }
    
    private static class NullLast<T> implements Comparator<T>{
        @Override
        public int compare(T o1, T o2) {
            if(o1==null){
                return 1;
            } else if(o2==null) {
                return -1;
            } else return 1;
        }
    }
}
