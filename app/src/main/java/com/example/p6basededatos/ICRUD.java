package com.example.p6basededatos;

public interface ICRUD {
    public void create(int id, String name);
    public void read();
    public void update(int id, String name);
    public void delete(int id);
}
