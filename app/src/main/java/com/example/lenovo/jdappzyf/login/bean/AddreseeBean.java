package com.example.lenovo.jdappzyf.login.bean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/20.
 */

public class AddreseeBean {


    /**
     * msg : 地址列表请求成功
     * code : 0
     * data : [{"addr":"河北鸡泽","addrid":14231,"mobile":123333233333,"name":"大牙","status":0,"uid":21241},{"addr":"北京八维","addrid":14260,"mobile":19919984344,"name":"彭孜帆","status":0,"uid":21241}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 河北鸡泽
         * addrid : 14231
         * mobile : 123333233333
         * name : 大牙
         * status : 0
         * uid : 21241
         */

        private String addr;
        private int addrid;
        private long mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
