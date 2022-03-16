/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pedro
 */
public class DAOEmpleado implements IDAOGeneral<Empleado> {

    @Override
    public boolean guardar(Empleado pojo) {
        TransactionDB<Empleado> transaction = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.save(pojo);
                    session.getTransaction().commit();

                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();

                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    session.close();
                }
                return re;
            }
        };

        return HibernateUtils.getInstance().execute(transaction);
    }

    @Override
    public boolean modificar(Empleado pojo) {
        TransactionDB<Empleado> transaction = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.update(pojo);
                    session.getTransaction().commit();
                    session.close();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };

        return HibernateUtils.getInstance().execute(transaction);

    }

    @Override
    public boolean borrar(long clave) {
        Empleado pojo = new Empleado();
        pojo.setClave(clave);
        TransactionDB<Empleado> transaction = new TransactionDB<Empleado>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.delete(pojo);
                    session.getTransaction().commit();
                    session.close();
                    re = true;
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    session.close();
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                return re;
            }
        };

        return HibernateUtils.getInstance().execute(transaction);

    }

    @Override
    public List<Empleado> consultar() {
        List<Empleado> listEmp = new ArrayList<>();

        SelectDB<Empleado> select = new SelectDB<Empleado>() {
            @Override
            public Query execute(SessionFactory sessionFactory) {
                Query query = null;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    query = session.createQuery("from Empleado ORDER BY clave");

                    session.getTransaction().commit();

                } catch (HibernateException ex) {
                    session.getTransaction().rollback();

                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    session.close();
                }
                return query;
            }
        };

        Query query = HibernateUtils.getInstance().select(select);
        listEmp = query.list();
        return listEmp;

    }

    @Override
    public Empleado buscarID(long clave) {
        Empleado empleado = null;
        
        empleado.setClave(clave);
        SelectDB<Empleado> select = new SelectDB<Empleado>(empleado) {
            @Override
            public Query execute(SessionFactory sessionFactory) {
                Query query = null;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    query = session.createQuery("from Empleado where clave = " + pojo.getClave());

                    session.getTransaction().commit();
                } catch (HibernateException ex) {
                    session.getTransaction().rollback();
                    
                    Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    session.close();
                }
                return query;
            }
        };
       Query query = HibernateUtils.getInstance().select(select);
        empleado = (Empleado) query.list().get(0);
        return empleado;
    }
}
