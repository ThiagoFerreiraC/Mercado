package br.com.sinqia.crud;


import java.sql.SQLException;

public interface Crud<T> {

    void create(T t) throws SQLException;

    T read(int id) throws SQLException;

    void update(int id, T t) throws SQLException;

    void delete(int id) throws SQLException;
}
