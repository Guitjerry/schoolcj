package com.xiaoyuan.controller;

import com.xiaoyuan.entity.*;
import com.xiaoyuan.respository.*;
import com.xiaoyuan.util.ExcelConfig;
import com.xiaoyuan.util.JsonUtilTemp;
import com.xiaoyuan.util.JxlExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化数据
 */
@Controller
@RequestMapping(value="/init")
public class InitController {
    @Autowired
    private TmBanjiRepository tmBanjiRepository;
    @Autowired
    private TmNianjiRepository tmNianjiRepository;
    @Autowired
    private TmStudentRepository tmStudentRepository;
    @Autowired
    private TmKemuRepository tmKemuRepository;
    @Autowired
    private TmTeacherRepository tmTeacherRepository;
    public List<Object> commonImportBack(InputStream in,String filename,String configxml,String tablename,Object target){
        List<Object> reportExcelDatas = new ArrayList<Object>();

        //反射解析得到相应的对象集合
        try{
            //解析对应配置文件，封装到对象
            String filePath = InitController.class.getClassLoader().getResource(configxml).getPath();

            ExcelConfig excelConfig = new JxlExcelUtil().combineExcelConfig(filePath,tablename);
            //反射解析得到相应的对象集合
            List<List<Object>> listob = new JxlExcelUtil().getBankListByJXLExcel(in,null, target,filePath,excelConfig);
            if(listob!=null&&listob.size()>0){
                for(List<Object> mainobj:listob){
                    for(Object obj:mainobj){
                        reportExcelDatas.add( target.getClass().cast(obj));
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reportExcelDatas;
    }
    @RequestMapping(value = "/initicon")
    public String initicon(HttpServletRequest request){
        return "init/icon";
    }
    /**
     * 初始化班级数据
     */
    @RequestMapping(value = "/initbanji")
    public void initBanji(HttpServletRequest request, HttpServletResponse response){
        try {
           File file = ResourceUtils.getFile("classpath:banji.xls");
            InputStream in = new FileInputStream(file);
            List<Object> objects = commonImportBack(in,"upfile","excelConfig.xml","TM_BANJI",new TmBanJi());
            for(Object object:objects){
                TmBanJi tmBanJi = (TmBanJi) object;
                TmBanJi tmBan = tmBanjiRepository.findOne(tmBanJi.getId());
                if(tmBan==null) {
                    tmBanjiRepository.save(tmBanJi);
                }
            }
            JsonUtilTemp.returnSucessJson(response,"导入数据成功");

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtilTemp.returnExceptionJson(response,e.getMessage());
        }


    }
    /**
     * 初始化年级数据
     */
    @RequestMapping(value = "/initnianji")
    public void initNianji(HttpServletRequest request, HttpServletResponse response){
        try {
            File file = ResourceUtils.getFile("classpath:nianji.xls");
            InputStream in = new FileInputStream(file);
            List<Object> objects = commonImportBack(in,"upfile","excelConfig.xml","TM_NIANJI",new TmNianji());
            for(Object object:objects){
                TmNianji tmNianji = (TmNianji) object;
                TmNianji tmNian = tmNianjiRepository.findOne(tmNianji.getId());
                if(tmNian==null){
                    tmNianjiRepository.save(tmNianji);
                }
            }
            JsonUtilTemp.returnSucessJson(response,"导入数据成功");

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtilTemp.returnExceptionJson(response,e.getMessage());
        }


    }
    /**
     * 初始化学生数据
     */
    @RequestMapping(value = "/initStudent")
    public void initStudent(HttpServletRequest request, HttpServletResponse response){
        try {
            File file = ResourceUtils.getFile("classpath:student.xls");
            InputStream in = new FileInputStream(file);
            List<Object> objects = commonImportBack(in,"upfile","excelConfig.xml","TM_STUDENT",new TmStudent(""));
            for(Object object:objects){
                TmStudent tmStudent = (TmStudent) object;
                tmStudent.setStatus(0);
                TmStudent student = tmStudentRepository.findOne(tmStudent.getId());
                if(student==null) {
                    tmStudentRepository.save(tmStudent);
                }
            }
            JsonUtilTemp.returnSucessJson(response,"导入数据成功");

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtilTemp.returnExceptionJson(response,e.getMessage());
        }


    }
    /**
     * 初始化学生数据
     */
    @RequestMapping(value = "/initKemu")
    public void initKemu(HttpServletRequest request, HttpServletResponse response){
        try {
            File file = ResourceUtils.getFile("classpath:kemu.xls");
            InputStream in = new FileInputStream(file);
            List<Object> objects = commonImportBack(in,"upfile","excelConfig.xml","TM_KEMU",new TmKemu());
            for(Object object:objects){
                TmKemu tmKemu = (TmKemu) object;
                TmKemu kemu = tmKemuRepository.findOne(tmKemu.getId());
                if(kemu==null) {
                    tmKemuRepository.save(tmKemu);
                }
            }
            JsonUtilTemp.returnSucessJson(response,"导入数据成功");

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtilTemp.returnExceptionJson(response,e.getMessage());
        }


    }

    /**
     * 初始化学生数据
     */
    @RequestMapping(value = "/initTeacher")
    public void initTeacher(HttpServletRequest request, HttpServletResponse response){
        try {
            File file = ResourceUtils.getFile("classpath:techer.xls");
            InputStream in = new FileInputStream(file);
            List<Object> objects = commonImportBack(in,"upfile","excelConfig.xml","TM_TEACHER",new TmTeacher());
            for(Object object:objects){
                TmTeacher tmTeacher = (TmTeacher) object;
                TmTeacher teacher = tmTeacherRepository.findOne(tmTeacher.getId());
                if(teacher==null) {
                    tmTeacherRepository.save(tmTeacher);
                }
            }
            JsonUtilTemp.returnSucessJson(response,"导入数据成功");

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtilTemp.returnExceptionJson(response,e.getMessage());
        }


    }
}
