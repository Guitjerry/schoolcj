package com.xiaoyuan.util;

/**
 * Created by dnys on 2017/9/12.
 */
public class Const {
    public enum DepthArray{

        ONE(1,"一级菜单"),
        TWO(2,"二级菜单");

        private int code;
        private String name;

        private DepthArray(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    public static  DepthArray[] getDepthArray(){
        return Const.DepthArray.values();
    }

}
