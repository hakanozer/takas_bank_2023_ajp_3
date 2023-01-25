package com.works.configs;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RootAwareInsertEventListener implements PersistEventListener {

    @Override
    public void onPersist(PersistEvent persistEvent) throws HibernateException {
        System.out.println("(PersistEvent persistEvent)");
    }

    @Override
    public void onPersist(PersistEvent persistEvent, Map map) throws HibernateException {
        System.out.println("(PersistEvent persistEvent, Map map)");
        onPersist(persistEvent);
    }

}
