package com.github.jmetzz.laboratory.generics.genericClass.vehicle;


public interface Convertible extends Vehicle {
    long timeToFold();
    void foldHood() throws InterruptedException;
    void unfoldHood() throws InterruptedException;
}
