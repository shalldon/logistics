package com.balidao.transreport.dao;

import com.balidao.transreport.dao.pager.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by double on 16-11-29.
 */
public interface IBaseDao<T> {
    /**
     * 添加对象
     * 
     * @param t
     * @return
     */
    public void save(T t);

    /**
     * 更新对象
     * 
     * @param t
     */
    public void update(T t);

    /**
     * 根据id删除对象
     * 
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据id加载对象
     * 
     * @param id
     * @return
     */
    public T get(Long id);


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
    public Pager<T> findPager(String hql, Object[] args, Map<String, Object> alias);

    public Pager<T> findPager(String hql, Object[] args);

    public Pager<T> findPager(String hql, Object arg);

    public Pager<T> findPager(String hql);

    public Pager<T> findPager(String hql, Map<String, Object> alias);


    /**
     * 查询单个对象
     *
     * @param hql
     * @param args  参数
     * @param alias 别名
     * @return
     */
    public Object findOneObject(String hql, Object[] args, Map<String, Object> alias);

    public Object findOneObject(String hql, Object[] args);

    public Object findOneObject(String hql, Object arg);

    public Object findOneObject(String hql);

    public Object findOneObject(String hql, Map<String, Object> alias);



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
    public List<T> findList(String hql, Object[] args, Map<String, Object> alias);

    public List<T> findList(String hql, Map<String, Object> alias);

    public List<T> findList(String hql, Object[] args);

    public List<T> findList(String hql, Object arg);

    public List<T> findList(String hql);


    /**
     * 根据hql更新数据库
     *
     * @param hql
     * @param args
     *            参数
     */
    public void updateByHql(String hql, Object[] args);

    public void updateByHql(String hql, Object arg);

    public void updateByHql(String hql);


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
            boolean hasEntity);

    public <N extends Object> List<N> findListBySql(String sql, Map<String, Object> alias, Class<?> clz,
            boolean hasEntity);

    public <N extends Object> List<N> findListBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity);

    public <N extends Object> List<N> findListBySql(String sql, Object arg, Class<?> clz, boolean hasEntity);

    public <N extends Object> List<N> findListBySql(String sql, Class<?> clz, boolean hasEntity);

    public <N extends Object> Pager<N> findPagerBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity);

    public <N extends Object> Pager<N> findPagerBySql(String sql, Object arg, Class<?> clz, boolean hasEntity);

    public <N extends Object> Pager<N> findPagerBySql(String sql, Class<?> clz, boolean hasEntity);


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
            Class<?> clz, boolean hasEntity);

    public <N extends Object> Pager<N> findPagerBySql(String sql, Map<String, Object> alias, Class<?> clz,
            boolean hasEntity);
}
