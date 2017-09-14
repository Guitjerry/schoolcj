package com.xiaoyuan.service;

import com.xiaoyuan.entity.TmResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by dnys on 2017/9/13.
 */
@Service(value = "tmResourceService")
public class TmResourceServiceImpl implements TmResourceService {
    @PersistenceContext
    protected EntityManager em;
    @Override
    public List<TmResource> findAllParent() {
        String sql = "select * from TM_RESOURCE where PARENT_ID is null";
        Query query =  em.createNativeQuery(sql,TmResource.class);
        return query.getResultList();
    }

    @Override
    public List<TmResource> findAllByParentid(Integer parentid) {
        String sql = "select * from TM_RESOURCE where PARENT_ID=?1";
        Query query =  em.createNativeQuery(sql,TmResource.class);
        query.setParameter(1,parentid);
        return query.getResultList();
    }
}
