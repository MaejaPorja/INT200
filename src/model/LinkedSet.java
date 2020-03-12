/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Iterator;
import java.util.Objects;
import model.base.GenericSet;

/**
 *
 * @author Student
 */
public class LinkedSet<T> extends GenericSet<T>  {
    
    private Node<T> first;
    private Node<T> last;
    
    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }
        public Node<E> getNext() {
            return next;
        }
        public Node<E> getPrev() {
            return prev;
        }

        public void setItem(E item) {
            this.item = item;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }       
    }
    
    private T addFirst(T t){
        Node<T> newNode = new Node(null, t, first);
        if(Objects.isNull(last)){
            last = newNode;
        }
        if(!Objects.isNull(first)){
            first.prev = newNode;
        }
        first = newNode;
        return t;
    }
    
    private T addLast(T t){
        Node<T> newNode = new Node(last, t, null);
        if(Objects.isNull(first)){
            first = newNode;
        }
        if(!Objects.isNull(last)){
            last.next = newNode;
        }
        last = newNode;
        return t;
    }
    
    @Override
    public T add(T t) {
        addLast(t);
        return t;
    }

    private T removeLast(){
        Node<T> tempNode = last;
        Node<T> prevNode = last.getPrev();
        if(prevNode!=null){
            last.setPrev(null);
            prevNode.setNext(null);
            last = prevNode;
        } else{
            last = null;
        }
       return tempNode.getItem();
    }
    
    private T removeFirst(){
        Node<T> tempNode = first;
        Node<T> nextNode = first.getNext();
        if(nextNode!=null){
            first.setNext(null);
            nextNode.setPrev(null);
            first = nextNode;
        } else {
            first = null;
        } return tempNode.getItem();
    }
    
    
    @Override
    public boolean delete(T t) {
        Objects.requireNonNull(t);
        if(first!=null&&last!=null){
            for(Node<T> node=first; node!=null; node=node.getNext()){
                if(t.equals(node.getItem())){
                    Node<T> prevNode = node.getPrev();
                    Node<T> nextNode = node.getNext();
                    if(!Objects.isNull(prevNode)){
                        prevNode.setNext(nextNode);
                        node.setPrev(null);
                    } else {
                        removeFirst();
                    }
                    if(!Objects.isNull(nextNode)){
                        nextNode.setPrev(prevNode);
                        nextNode.setNext(null);
                    } else {
                        removeLast();
                    }
                    
                    return true;
                }
            }
        } return false;
    }

    @Override
    public int find(T t) {
        Objects.requireNonNull(t);
        int index = 0;
        for(Iterator<T>itr=this.iterator(); itr.hasNext(); index++){
            if(t.equals(itr.next())){
                return index;
            }
        } return -1;
    }
    
    @Override
    public Iterator<T> iterator(){
        Node<T> first = this.first;
        return new Iterator<T>(){
            Node<T> node = first;
            @Override
            public boolean hasNext() {
                return node!=null;
            }
            @Override
            public T next() {
                T item = node.item;
                node = node.next;
                return item;
            }
        };
    }
}
