package org.example;

import java.util.*;

public class MyArrayList<E> {

    private static final int CAPACITY = 8;
    private Object container[] = {};
    private int size = 0;

    public MyArrayList(){
        container = new Object[CAPACITY];
    }

    private void growth(){size++;}

    private void reduce(){size--;}

    public int size(){return size;}

    public boolean isEmpty() {return size == 0;}

    public boolean remove(E o) {
        for (int i = 0; i <= size - 1; i++) {
            if(container[i] == o){
                for (int j = i; j <= size - 1 ; j++) {
                    container[j] = container[j+1];
                }
                reduce();
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        Object element = container[index];
        for (int i = index; i <= size - 1 ; i++) {
            container[i] = container[i+1];
        }
        reduce();
        return (E)element;
    }

    public void clear() {
        Arrays.fill(container, 0, size, null);
        size = 0;
    }

    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        return (E) container[index];
    }

    public void add(int index, E element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        if(size == container.length){
            ensureCapacity();
        }
        for (int i = size - 1; i >= index ; i--) {
            container[i+1] = container[i];
        }
        container[index] = element;
        growth();
    }

    public void sort(){
        MergeSort.mergeSort(container);
    }

    public boolean addAll(Collection<? extends E> c){
        Object[] array = c.toArray();
        int sizeOfArray = array.length;
        if(array.length == 0){
            return false;
        }
        while(container.length - size < array.length){
            ensureCapacity();
        }
        System.arraycopy(array, 0, container, size, sizeOfArray);
        size += sizeOfArray;
        return true;
    }

    private void ensureCapacity(){
        int newCapacity = container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

}
