package com.github.jmetzz.laboratory.generics.genericClass.tuple;

public interface HomogeneousPair<T> {
    T getLeft();
    T getRight();
    void setLeft(T value);
    void setRight(T value);
}
