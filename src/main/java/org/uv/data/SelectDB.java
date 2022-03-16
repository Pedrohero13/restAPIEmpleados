package org.uv.data;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedro
 */
public abstract class SelectDB<T> {
    protected T pojo;
    public SelectDB() {
    }

    public SelectDB(T pojo) {
        this.pojo = pojo;
    }
    public abstract Query execute(SessionFactory sessionFactory);
}
