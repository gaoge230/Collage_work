package org.ssh.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 数据库io操作  ，主要实现数据库的操作，这里只是一些简单的实现
 * @author gaoge
 * @Time 2017.2.21
 *
 */
public class BaseDao extends HibernateDaoSupport {
    /**
     * 根据queryString查询
     * @param queryString
     * @return
     */
    public <T> List<T> getObjects(String queryString){  
    	System.out.println("C");
        return (List<T>)this.getHibernateTemplate().find(queryString);  
    }  
      
    /**
     * 查询所有 
     * @param clazz
     * @return
     */
    public <T> List<T> getAllObjects(Class<T> clazz){ 
    	
        return this.getHibernateTemplate().loadAll(clazz);  
    }  
      
    /**
     * 添加一个 
     * @param clazz
     */
    public <T> void addObject(T clazz){  
        this.getHibernateTemplate().save(clazz);  
    }  
      
    /**
     * 更新指定的
     * @param clazz
     */
    public <T> void updateObject(T clazz){  
        this.getHibernateTemplate().update(clazz);  
    }  
  
    /**
     *  删除指定的  
     * @param clazz
     */
    public <T> void deleteObject(T clazz){  
        this.getHibernateTemplate().delete(clazz);  
    }  
      
    /**
     * 通过id获取
     * @param clazz
     * @param id
     * @return
     */
    public <T> T getObject(Class<T> clazz,Serializable id){  
        return this.getHibernateTemplate().get(clazz, id);  
    } 
}
