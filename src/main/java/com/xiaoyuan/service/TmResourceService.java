package com.xiaoyuan.service;

import com.xiaoyuan.entity.TmResource;

import java.util.List;

/**
 * Created by dnys on 2017/9/13.
 */
public interface TmResourceService {
    //查找所有父类菜单
    public List<TmResource> findAllParent();
    //查找所有子节点
    public List<TmResource> findAllByParentid(Integer parentid);
}
