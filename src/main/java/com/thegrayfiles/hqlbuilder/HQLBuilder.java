package com.thegrayfiles.hqlbuilder;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class HQLBuilder {

    private Session session;
    private StringBuilder hqlString;

    private HQLBuilder(Session session) {
        this.session = session;
        hqlString = new StringBuilder();
    }

    public static HQLBuilder hql(Session session) {
        return new HQLBuilder(session);
    }

    public HQLBuilder from(String tableName) {
        hqlString.append(" FROM " + tableName);
        return this;
    }

    public HQLBuilder where(HQLCondition condition) {
        hqlString.append(" WHERE " + condition.toHqlString());
        return this;
    }

    public <T> T uniqueResult(Class<T> clazz) {
        return (T) session.createQuery(hqlString.toString()).uniqueResult();
    }

    public <T> List<T> list(Class<T> clazz) {
        ArrayList<T> typedList = new ArrayList<T>();
        List untypedList = session.createQuery(hqlString.toString()).list();
        for (Object item : untypedList) {
            typedList.add((T) item);
        }
        return typedList;
    }
}
