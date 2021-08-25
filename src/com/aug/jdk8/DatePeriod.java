package com.aug.jdk8;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 一些时间周期
 */
public class DatePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间，字符串格式
     */
    private String startDateStr;

    /**
     * 结束时间，字符串格式
     */
    private String EndDateStr;

    /**
     * 开始时间
     */
    private LocalDate startLocalDate;

    /**
     * 结束时间
     */
    private LocalDate endLocalDate;

    /**
     * 环比开始时间，字符串格式
     */
    private String startDateOfQoQStr;

    /**
     * 环比结束时间，字符串格式
     */
    private String EndDateOfQoQStr;

    /**
     *环比时间-开始日期
     */
    private LocalDate startLocalDateOfQoQ ;

    /**
     * 环比时间-结束日期
     */
    private LocalDate endLocalDateOfQoQ ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return EndDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        EndDateStr = endDateStr;
    }

    public LocalDate getStartLocalDate() {
        return startLocalDate;
    }

    public void setStartLocalDate(LocalDate startLocalDate) {
        this.startLocalDate = startLocalDate;
    }

    public LocalDate getEndLocalDate() {
        return endLocalDate;
    }

    public void setEndLocalDate(LocalDate endLocalDate) {
        this.endLocalDate = endLocalDate;
    }

    public String getStartDateOfQoQStr() {
        return startDateOfQoQStr;
    }

    public void setStartDateOfQoQStr(String startDateOfQoQStr) {
        this.startDateOfQoQStr = startDateOfQoQStr;
    }

    public String getEndDateOfQoQStr() {
        return EndDateOfQoQStr;
    }

    public void setEndDateOfQoQStr(String endDateOfQoQStr) {
        EndDateOfQoQStr = endDateOfQoQStr;
    }

    public LocalDate getStartLocalDateOfQoQ() {
        return startLocalDateOfQoQ;
    }

    public void setStartLocalDateOfQoQ(LocalDate startLocalDateOfQoQ) {
        this.startLocalDateOfQoQ = startLocalDateOfQoQ;
    }

    public LocalDate getEndLocalDateOfQoQ() {
        return endLocalDateOfQoQ;
    }

    public void setEndLocalDateOfQoQ(LocalDate endLocalDateOfQoQ) {
        this.endLocalDateOfQoQ = endLocalDateOfQoQ;
    }
}
