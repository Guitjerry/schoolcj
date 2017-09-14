package com.xiaoyuan.controller;

import com.xiaoyuan.entity.TmRole;
import com.xiaoyuan.respository.TmRoleRepository;
import com.xiaoyuan.util.JsonUtilTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色管理控制类
 */
@Controller
@RequestMapping("/role")
public class TmRoleMainController {
    @Autowired
    private TmRoleRepository tmRoleRepository;
    /**
     * 添加角色
     */
    @RequestMapping("addRole")
    private String addRole(HttpServletResponse response){

       return "role/addRole";
    }
    @RequestMapping("addRoleSure")
    private void addRoleSure(HttpServletResponse response, TmRole tmRole){
        if(tmRole!=null){
            tmRoleRepository.save(tmRole);
        }
        if(tmRole!=null&&tmRole.getId()>0){
            JsonUtilTemp.returnSucessJson(response,"保存成功");
        }
    }
    /**
     * 编辑角色
     */
    @RequestMapping("editRole")
    private String editRole(Integer roleid,HttpServletRequest request){
        if(roleid!=null){
            TmRole tmRole = tmRoleRepository.findOne(roleid);
            request.setAttribute("role",tmRole);
        }
        return "role/editRole";
    }
    /**
     * 角色列表
     */
    @RequestMapping("/roleList")
    private String roleList(HttpServletRequest request,String msg){
        request.setAttribute("msg",msg);
        List<TmRole> tmRoles = tmRoleRepository.findAll();
        request.setAttribute("roles",tmRoles);
        return "role/list";
    }
    /**
     * 角色列表
     */
    @RequestMapping("/roleListJson")
    private void roleListJson(HttpServletRequest request,HttpServletResponse response,String msg){
        request.setAttribute("msg",msg);
        List<TmRole> tmRoles = tmRoleRepository.findAll();
        JsonUtilTemp.returnJson(tmRoles,response);
    }
    /**
     * 分配资源
     */
    @RequestMapping("roletoResource")
    private void RoleToResource(){

    }
}
