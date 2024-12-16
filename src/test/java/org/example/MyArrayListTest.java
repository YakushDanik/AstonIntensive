package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MyArrayListTest {

    MyArrayList<Integer> myArrayList;

    @BeforeEach
    void setUp() {
        myArrayList = new MyArrayList<>();
    }

    @Test
    void sizeTest() {
        assertEquals(0, myArrayList.size());
        myArrayList.add(0,1);
        assertEquals(1, myArrayList.size());
    }

    @Test
    void isEmptyTest() {
        assertEquals(true, myArrayList.isEmpty());
        myArrayList.add(0,1);
        assertEquals(false, myArrayList.isEmpty());
    }

    @Test
    void removeTest() {
        myArrayList.add(0,50);
        myArrayList.add(1,100);
        assertEquals(50, myArrayList.get(0));
        assertEquals(100, myArrayList.get(1));

        //test removing by index
        myArrayList.remove(0);
        assertEquals(100, myArrayList.get(0));

        //test removing by element
        myArrayList.add(0,50);
        myArrayList.remove((Integer)50);
        assertEquals(100, myArrayList.get(0));

    }


    @Test
    void clearTest() {
        myArrayList.add(0, 5);
        myArrayList.add(1, 10);
        myArrayList.clear();
        assertEquals(0, myArrayList.size());
        assertTrue(myArrayList.isEmpty());
    }

    @Test
    void addAndGetTest() {
        myArrayList.add(0, 5);
        myArrayList.add(1, 10);
        assertEquals(5, myArrayList.get(0));
        assertEquals(10, myArrayList.get(1));
        assertEquals(2, myArrayList.size());
    }

    @Test
    void sortTest() {
        myArrayList.add(0, 5);
        myArrayList.add(1, 1);
        myArrayList.add(2, 3);
        myArrayList.sort();

        assertEquals(1, myArrayList.get(0));
        assertEquals(3, myArrayList.get(1));
        assertEquals(5, myArrayList.get(2));
    }

    @Test
    void addAllTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        boolean isAdded = myArrayList.addAll(list);
        assertTrue(isAdded);
        assertEquals(3, myArrayList.size());
        assertEquals(1, myArrayList.get(0));
        assertEquals(2, myArrayList.get(1));
        assertEquals(3, myArrayList.get(2));
    }
}