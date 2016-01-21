package ru.broject.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.broject.site.dao.TestDao;
import ru.broject.site.service.TestService;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public int testConnection() {
        return testDao.testConnection();
    }
}
