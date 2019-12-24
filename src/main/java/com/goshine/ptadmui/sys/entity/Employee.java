package com.goshine.ptadmui.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工Model
 * @author goshine
 */
@SuppressWarnings("serial")
public class Employee implements Serializable{
    private Long employeeId;//ID
    private String name;//名称
    private String title;//职位
    private String base;//工作地点
    private Integer age;//年龄
    private Date hireDate;//入职日期
    private Long salary;//年薪

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base == null ? null : base.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}