package com.qixin.example.entity;

import com.sun.xml.internal.bind.annotation.XmlLocation;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * 创 建 时 间: 2019/4/9
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@ToString
public class FileInfo implements Serializable {

    private String fileName;
    private Integer fileAge;

    @XmlElement(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlElement(name = "file_age")
    public Integer getFileAge() {
        return fileAge;
    }

    public void setFileAge(Integer fileAge) {
        this.fileAge = fileAge;
    }
}
