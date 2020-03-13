/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.base;


/**
 *
 * @author Student
 * @param <T>
 */
public abstract class GenericSet<T> implements Iterable<T> {
    abstract public T add(T t);
    abstract public boolean delete(T t);
    abstract public int find(T t);
    protected transient int modCount;
}
