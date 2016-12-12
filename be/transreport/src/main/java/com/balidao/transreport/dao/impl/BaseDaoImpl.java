package com.balidao.transreport.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.balidao.transreport.dao.IBaseDao;
import com.balidao.transreport.dao.pager.Pager;
import com.balidao.transreport.dao.pager.PagerContext;

/**
 * Created by double on 16-11-29.
 */
public class BaseDaoImpl<T> implements IBaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 创建一个Class的对象来获取泛型的class
     */
    private Class<?> clz;

    public Class<?> getClz() {
        if (clz == null) {
            // 获取泛型的Class对象
            clz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[0]));
        }
        return clz;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void delete(Long id) {
        getSession().delete(this.get(id));
    }

    @Override
    public T get(Long id) {
        return (T) getSession().load(getClz(), id);
    }

    /**
     * 包含分页的查询
     * 
     * @param hql
     * @param args
     *            参数
     * @param alias
     *            别名
     * @return
     */
    public Pager<T> findPager(String hql, Object[] args, Map<String, Object> alias) {
        String cq = getCountHql(hql, true);
        hql = initSort(hql);
        Query cquery = getSession().createQuery(cq);
        Query query = getSession().createQuery(hql);
        // 设置别名参数
        setAliasParameter(query, alias);
        setAliasParameter(cquery, alias);
        // 设置参数
        setParameter(query, args);
        setParameter(cquery, args);
        Pager<T> pages = new Pager<T>();
        setPagers(query, pages);
        List<T> datas = query.list();
        pages.setDatas(datas);
        long total = (Long) cquery.uniqueResult();
        pages.setTotalSize(total);
        return pages;
    }

    public Pager<T> findPager(String hql, Object[] args) {
        return this.findPager(hql, args, null);
    }

    public Pager<T> findPager(String hql, Object arg) {
        return this.findPager(hql, new Object[] { arg }, null);
    }

    public Pager<T> findPager(String hql) {
        return this.findPager(hql, null, null);
    }

    public Pager<T> findPager(String hql, Map<String, Object> alias) {
        return this.findPager(hql, null, alias);
    }

    /**
     * 根据hql 生成count的 hql
     *
     * @param hql
     * @param isHql
     * @return
     */
    private String getCountHql(String hql, boolean isHql) {
        String e = hql.substring(hql.indexOf("from"));
        String c = "select count(*) " + e;
        if (isHql)
            c = c.replaceAll("fetch", "");
        return c;
    }

    /**
     * 查询单个对象
     * 
     * @param hql
     * @param args
     * @param alias
     * @return
     */
    public Object findOneObject(String hql, Object[] args, Map<String, Object> alias) {
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.uniqueResult();
    }

    public Object findOneObject(String hql, Object[] args) {
        return this.findOneObject(hql, args, null);
    }

    public Object findOneObject(String hql, Object arg) {
        return this.findOneObject(hql, new Object[] { arg }, null);
    }

    public Object findOneObject(String hql) {
        return this.findOneObject(hql, null, null);
    }

    public Object findOneObject(String hql, Map<String, Object> alias) {
        return this.findOneObject(hql, null, alias);
    }

    /**
     * 不分页的查询
     * 
     * @param hql
     * @param args
     *            参数
     * @param alias
     *            别名
     * @return 返回列表
     */
    public List<T> findList(String hql, Object[] args, Map<String, Object> alias) {
        hql = initSort(hql);
        Query query = getSession().createQuery(hql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        return query.list();
    }

    public List<T> findList(String hql, Map<String, Object> alias) {
        return this.findList(hql, null, alias);
    }

    public List<T> findList(String hql, Object[] args) {
        return this.findList(hql, args, null);
    }

    public List<T> findList(String hql, Object arg) {
        return this.findList(hql, new Object[] { arg }, null);
    }

    public List<T> findList(String hql) {
        return this.findList(hql, null, null);
    }

    /**
     * 根据hql更新数据库
     * 
     * @param hql
     * @param args
     *            参数
     */
    public void updateByHql(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        query.executeUpdate();
    }

    public void updateByHql(String hql, Object arg) {
        this.updateByHql(hql, new Object[] { arg });
    }

    public void updateByHql(String hql) {
        this.updateByHql(hql, null);
    }

    /**
     * 原生的sql查询出list
     *
     * @param sql
     * @param args
     *            参数
     * @param alias
     *            别名
     * @param clz
     *            clz
     * @param hasEntity
     *            clz是否有对应表
     * @param <N>
     * @return
     */
    public <N extends Object> List<N> findListBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz,
            boolean hasEntity) {
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(sql);
        setAliasParameter(sq, alias);
        setParameter(sq, args);
        if (hasEntity) {
            sq.addEntity(clz);
        } else
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        return sq.list();
    }

    public <N extends Object> List<N> findListBySql(String sql, Map<String, Object> alias, Class<?> clz,
            boolean hasEntity) {
        return this.findListBySql(sql, null, alias, clz, hasEntity);
    }

    public <N extends Object> List<N> findListBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.findListBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> List<N> findListBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.findListBySql(sql, new Object[] { arg }, null, clz, hasEntity);
    }

    public <N extends Object> List<N> findListBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.findListBySql(sql, null, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findPagerBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {
        return this.findPagerBySql(sql, args, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findPagerBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {
        return this.findPagerBySql(sql, new Object[] { arg }, null, clz, hasEntity);
    }

    public <N extends Object> Pager<N> findPagerBySql(String sql, Class<?> clz, boolean hasEntity) {
        return this.findPagerBySql(sql, null, null, clz, hasEntity);
    }

    /**
     * 根据原生的sql查询,并且分页
     * 
     * @param sql
     * @param args
     * @param alias
     * @param clz
     * @param hasEntity
     * @param <N>
     * @return
     */
    public <N extends Object> Pager<N> findPagerBySql(String sql, Object[] args, Map<String, Object> alias,
            Class<?> clz, boolean hasEntity) {
        String cq = getCountHql(sql, false);
        sql = initSort(sql);
        SQLQuery sq = getSession().createSQLQuery(sql);
        SQLQuery cquery = getSession().createSQLQuery(cq);
        setAliasParameter(sq, alias);
        setAliasParameter(cquery, alias);
        setParameter(sq, args);
        setParameter(cquery, args);
        Pager<N> pages = new Pager<N>();
        setPagers(sq, pages);
        if (hasEntity) {
            sq.addEntity(clz);
        } else {
            sq.setResultTransformer(Transformers.aliasToBean(clz));
        }
        List<N> datas = sq.list();
        pages.setDatas(datas);
        long total = ((BigInteger) cquery.uniqueResult()).longValue();
        pages.setTotalSize(total);
        return pages;
    }

    public <N extends Object> Pager<N> findPagerBySql(String sql, Map<String, Object> alias, Class<?> clz,
            boolean hasEntity) {
        return this.findPagerBySql(sql, null, alias, clz, hasEntity);
    }

    /**
     * 给查询设置分页
     *
     * @param query
     * @param pages
     */
    @SuppressWarnings("rawtypes")
    private void setPagers(Query query, Pager pages) {
        Integer pageSize = PagerContext.getPageSize();
        Integer startPage = PagerContext.getStartPage();
        if (startPage == null || startPage < 0)
            startPage = 0;
        if (pageSize == null || pageSize < 0)
            pageSize = 15;
        pages.setStartPage(startPage);
        pages.setPageSize(pageSize);
        query.setFirstResult(startPage).setMaxResults(pageSize);
    }

    /**
     * 设置别名
     *
     * @param query
     * @param alias
     *            别名和参数对应的map
     */
    @SuppressWarnings("rawtypes")
    private void setAliasParameter(Query query, Map<String, Object> alias) {
        if (alias != null) {
            Set<String> keys = alias.keySet();
            for (String key : keys) {
                Object val = alias.get(key);
                if (val instanceof Collection) {
                    // 查询条件是列表
                    query.setParameterList(key, (Collection) val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    /**
     * 设置参数
     *
     * @param query
     * @param args
     *            参数的数组
     */
    private void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }
    }

    private String initSort(String hql) {
        String order = PagerContext.getOrder();
        String sort = PagerContext.getSort();
        if (sort != null && !"".equals(sort.trim())) {
            hql += " order by " + sort;
            if (!"desc".equals(order))
                hql += " asc";
            else
                hql += " desc";
        }
        return hql;
    }
}
