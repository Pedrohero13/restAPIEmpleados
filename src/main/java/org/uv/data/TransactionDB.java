/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author pedro
 */
public abstract class TransactionDB<T> {
    protected T pojo;
    public TransactionDB(T pojo) {
        this.pojo = pojo;
    }    

    public TransactionDB() {
    }
    
    public abstract boolean execute(SessionFactory sessionFactory);
}
