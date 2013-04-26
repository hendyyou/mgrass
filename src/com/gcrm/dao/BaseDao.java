/**
 * Copyright (C) 2012, Grass CRM Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcrm.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gcrm.vo.SearchCondition;
import com.gcrm.vo.SearchResult;

/**
 * Base Dao
 */
public class BaseDao<T extends Serializable> extends HibernateDaoSupport
        implements IBaseDao<T> {

    private static String INIT_HQL = "from ";

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#getAllObjects(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllObjects(String clazz) {

        String hql = INIT_HQL + clazz;
        List<T> objects = null;

        objects = getHibernateTemplate().find(hql);

        return objects;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllSortedObjects(String clazz, String sortColumn,
            String order) {

        String hql = INIT_HQL + clazz + " order by " + sortColumn + " " + order;
        List<T> objects = null;

        objects = getHibernateTemplate().find(hql);

        return objects;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#findByParam(java.lang.String,
     * java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public List<T> findByParam(String hql, Object paramValue) {

        List<T> objects = null;

        objects = getHibernateTemplate().find(hql, paramValue);
        return objects;
    }

    @SuppressWarnings("unchecked")
    public List<T> findByHQL(String hql) {

        List<T> objects = null;

        objects = getHibernateTemplate().find(hql);
        return objects;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#findByName(java.lang.String, java.lang.String)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public T findByName(String clazz, String name) {
        String hql = INIT_HQL + clazz + " where name = ?";
        T object = null;
        List result = null;

        result = findByParam(hql, name);
        if (result != null && result.size() > 0) {
            object = (T) result.get(0);
        }
        return object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#makePersistent(java.io.Serializable)
     */
    public T makePersistent(T entity) {
        T result = getHibernateTemplate().merge(entity);
        getHibernateTemplate().flush();
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#batchUpdate(java.util.Collection)
     */
    public void batchUpdate(Collection<T> entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
        getHibernateTemplate().flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#deleteEntity(java.lang.Class,
     * java.lang.Integer)
     */
    public void deleteEntity(Class<T> entityClass, Integer id) {
        T entity = getHibernateTemplate().get(entityClass, id);
        getHibernateTemplate().delete(entity);
        getHibernateTemplate().flush();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#getObjectsCount(java.lang.String)
     */
    public long getObjectsCount(String clazz) {
        String hql = "select count(*) from " + clazz;

        long count = 0;

        count = (Long) getHibernateTemplate().find(hql).get(0);

        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#getEntityById(java.lang.Class,
     * java.lang.Integer)
     */
    public T getEntityById(Class<T> entityClass, Integer id) {
        T entity = null;
        entity = getHibernateTemplate().load(entityClass, id);
        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.gcrm.dao.IBaseDao#getPaginationObjects(java.lang.String,
     * com.gcrm.vo.SearchCondition)
     */
    @SuppressWarnings("unchecked")
    public SearchResult<T> getPaginationObjects(final String clazz,
            final SearchCondition searchCondition) {

        String hql = INIT_HQL + clazz;
        SearchResult<T> result = getPaginationObjectsWithHql(clazz, hql,
                searchCondition);

        return result;
    }

    @SuppressWarnings("unchecked")
    public SearchResult<T> getPaginationObjectsWithHql(final String clazz,
            final String hql, final SearchCondition searchCondition) {

        List<T> objects = null;

        final String condition = searchCondition.getCondition();

        objects = getHibernateTemplate().executeFind(
                new HibernateCallback<List<T>>() {

                    public List<T> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuilder hqlBuilder = new StringBuilder(hql);
                        if (condition != null && condition.length() > 0) {
                            hqlBuilder.append(" where ");
                            hqlBuilder.append(condition);
                        }
                        hqlBuilder.append(" order by ")
                                .append(searchCondition.getSidx()).append(" ")
                                .append(searchCondition.getSord());
                        int pageSize = searchCondition.getPageSize();
                        int pageNo = searchCondition.getPageNo();

                        Query query = session.createQuery(hqlBuilder.toString());

                        if (pageNo != 0 && pageSize != 0) {
                            int rowNumber = (pageNo - 1) * pageSize;
                            query.setFirstResult(rowNumber);
                            query.setMaxResults(pageSize);
                        }
                        List<T> list = query.list();

                        return list;
                    }
                });

        long count = 0;
        String countHql = "select count(*) from " + clazz;
        if (condition != null && condition.length() > 0) {
            countHql += " where ";
            countHql += condition;
        }

        count = (Long) getHibernateTemplate().find(countHql).get(0);
        SearchResult<T> result = new SearchResult<T>(count, objects);

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> getObjects(final String clazz, final String condition) {

        List<T> objects = null;

        objects = getHibernateTemplate().executeFind(
                new HibernateCallback<List<T>>() {

                    public List<T> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        String hql = INIT_HQL + clazz;
                        if (condition != null && condition.length() > 0) {
                            hql += " where ";
                            hql += condition;
                        }
                        Query query = session.createQuery(hql);

                        List<T> list = query.list();

                        return list;
                    }
                });

        return objects;
    }

}
