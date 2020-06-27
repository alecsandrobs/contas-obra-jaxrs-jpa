package com.stolk.alecsandro.obra.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Dao<E> {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private Class<E> classe;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("obra");
        } catch (Exception e) {
            // logar -> log4j
        }
    }

    public Dao() {
        this(null);
    }

    public Dao(Class<E> classe) {
        this.classe = classe;
        em = emf.createEntityManager();
    }

    public Dao<E> iniciar() {
        em.getTransaction().begin();
        return this;
    }

    public Dao<E> encerrar() {
        em.getTransaction().commit();
        return this;
    }

    public Dao<E> cadastrar(E entidade) {
        em.persist(entidade);
        return this;
    }

    public Dao<E> editar(E entidade) {
        em.merge(entidade);
        return this;
    }

    public void excluir(E entidade) {
        em.remove(entidade);
    }

    public void remover(Long id) {
        E entidade = em.find(classe, id);
        em.remove(entidade);
    }

    public E buscarUm(Object id) {
        return em.find(classe, id);
    }

    public List<E> buscar() {
        return this.buscar(100, 0);
    }

    public List<E> buscar(int quantidade, int deslocamento) {
        if (classe == null) {
            throw new UnsupportedOperationException("A classe está nula.");
        }
        String jpql = String.format("select e from %s e", classe.getName());
        TypedQuery<E> query = em.createQuery(jpql, classe);
        query.setMaxResults(quantidade).setFirstResult(deslocamento);
        return query.getResultList();
    }

    public List<E> buscarSql(String nome, Object... params) {
        TypedQuery<E> query = em.createNamedQuery(nome, classe);
        for (int i = 0; i < params.length; i += 2) {
            query.setParameter(params[i].toString(), params[i + 1]);
        }
        return query.getResultList();
    }

    public E buscarUmSql(String nome, Object... params) {
        List<E> lista = buscarSql(nome, params);
        return lista.isEmpty() ? null : lista.get(0);
    }

    public void fechar() {
        em.close();
    }
}
