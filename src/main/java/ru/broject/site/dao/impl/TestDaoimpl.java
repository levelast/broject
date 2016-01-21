package ru.broject.site.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.broject.site.dao.TestDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Repository
public class TestDaoimpl implements TestDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public int testConnection() {
        try {
            return (Integer) em.createNativeQuery("SELECT 1").getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }
}
