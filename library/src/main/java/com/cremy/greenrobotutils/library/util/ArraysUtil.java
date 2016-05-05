package com.cremy.greenrobotutils.library.util;

import java.lang.reflect.Array;

/**
 * Created by remychantenay on 16/02/2016.
 */
public final class ArraysUtil {
    public static final int INDEX_NOT_FOUND = -1;


    /**
     * Allows to remove an element from an array with a given value
     * @param _array
     * @param _value (int)
     * @return
     */
    public static int [] removeElement(int[] _array, int _value) {
        final int length = _array.length;
        final int index = indexOf(_array, _value);

        if (index == INDEX_NOT_FOUND) {
            return _array;
        }

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        int[] newArray = (int[]) Array.newInstance(int.class, length - 1);
        System.arraycopy(_array, 0, newArray, 0, index);
        if (index < length - 1) {
            System.arraycopy(_array, index + 1, newArray, index, length - index - 1);
        }

        return newArray;
    }

    /**
     * Allows to remove an element from an array with a given index
     * @param _array
     * @param _index (int)
     * @return
     */
    public static int [] removeElementWithIndex(int[] _array, int _index) {
        final int length = _array.length;

        if (_index == INDEX_NOT_FOUND) {
            return _array;
        }

        if (_index < 0 || _index >= length) {
            throw new IndexOutOfBoundsException("Index: " + _index + ", Length: " + length);
        }

        int[] newArray = (int[]) Array.newInstance(int.class, length - 1);
        System.arraycopy(_array, 0, newArray, 0, _index);
        if (_index < length - 1) {
            System.arraycopy(_array, _index + 1, newArray, _index, length - _index - 1);
        }

        return newArray;
    }


    /**
     * Allows to get the index of a given element in a given array
     * @param array
     * @param valueToFind (int)
     * @return
     */
    public static int indexOf(final int[] array, final int valueToFind) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }

        for (int i = 0; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }


}
