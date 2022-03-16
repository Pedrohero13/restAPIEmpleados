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

/**
 *
 * @author pedro
 */
public class DAODepartamento implements IDAOGeneral<Departamento> {

    @Override
    public boolean guardar(Departamento pojo) {
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
            @Override
            public boolean execute(SessionFactory sessionFactory) {
                boolean re = false;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    session.save(pojo);
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
    public boolean modificar(Departamento pojo) {
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
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
        Departamento pojo = new Departamento();
        pojo.setClave(clave);
        TransactionDB<Departamento> transaction = new TransactionDB<Departamento>(pojo) {
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
    public List<Departamento> consultar() {
        List<Departamento> listDpm = new ArrayList<>();
        SelectDB<Departamento> select = new SelectDB<Departamento>() {
            @Override
            public Query execute(SessionFactory sessionFactory) {
                Query query = null;
                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();
                    query = session.createQuery("from Departamento ORDER BY clave");

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
        listDpm= query.list();
        return listDpm;

    }

    @Override
    public Departamento buscarID(long clave) {
        Departamento departamento = null;
        
        departamento.setClave(clave);
        SelectDB<Departamento> select = new SelectDB<Departamento>(departamento) {
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
                    
                    Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    session.close();
                }
                return query;
            }
        };
       Query query = HibernateUtils.getInstance().select(select);
        departamento = (Departamento) query.list().get(0);
        return departamento;

    }
}
