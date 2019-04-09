package com.qixin.example.entity;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 创 建 时 间: 2019/4/9
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@ToString
public class FileSize implements Serializable {

    private Integer id;
    private String[] fileName;
    private Integer[] fileAge;

    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "file_name")
    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    @XmlElement(name = "file_age")
    public Integer[] getFileAge() {
        return fileAge;
    }

    public void setFileAge(Integer[] fileAge) {
        this.fileAge = fileAge;
    }
}
