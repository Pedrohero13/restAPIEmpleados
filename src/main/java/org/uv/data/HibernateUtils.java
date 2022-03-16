/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.data;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author pedro
 */
public class HibernateUtils {

    private static HibernateUtils cx = null;//singleton

    public static HibernateUtils getInstance() {
        if (cx == null) {
            cx = new HibernateUtils();
        }
        return cx;
    }
    private SessionFactory sessionFactory;

    private HibernateUtils() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            Logger.getLogger(HibernateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean execute(TransactionDB transaction) {
        boolean response = transaction.execute(sessionFactory);
        return response;
    }

    public Query select(SelectDB transaction) {
        Query re = transaction.execute(sessionFactory);
        return re;
    }
}
