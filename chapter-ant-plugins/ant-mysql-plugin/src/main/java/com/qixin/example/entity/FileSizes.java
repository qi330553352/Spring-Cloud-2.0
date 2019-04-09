package com.qixin.example.entity;

import com.sun.xml.internal.bind.annotation.XmlLocation;
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
@XmlRootElement(name = "file_sizes")
public class FileSizes implements Serializable {


    private List<FileSize> fileSizes = new ArrayList<>();

    @XmlElements({@XmlElement(name = "file_size", type = FileSize.class)})
    public List<FileSize> getFileSizes() {
        return fileSizes;
    }

    public void setFileSizes(List<FileSize> fileSizes) {
        this.fileSizes = fileSizes;
    }
}
