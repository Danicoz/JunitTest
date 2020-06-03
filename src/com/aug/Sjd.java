package com.aug;

import java.util.Date;

public class Sjd {
    private String kssj;
    private String jssj;
    private Date kssjDate;
    private Date jssjDate;

    public Sjd(){}

    public Sjd(String kssj, String jssj) {
        this.kssj = kssj;
        this.jssj = jssj;
        this.kssjDate = kssjDate;
        this.jssjDate = jssjDate;
    }

    public String getKssj() {
        return kssj;
    }
    public void setKssj(String kssj) {
        this.kssj = kssj;
    }
    public String getJssj() {
        return jssj;
    }
    public void setJssj(String jssj) {
        this.jssj = jssj;
    }
    public Date getKssjDate() {
        return kssjDate;
    }
    public void setKssjDate(Date kssjDate) {
        this.kssjDate = kssjDate;
    }
    public Date getJssjDate() {
        return jssjDate;
    }
    public void setJssjDate(Date jssjDate) {
        this.jssjDate = jssjDate;
    }

    public String toString(){
        return "{kssj:"+this.kssj+",jssj:"+this.getJssj()+"}";
    }

}
