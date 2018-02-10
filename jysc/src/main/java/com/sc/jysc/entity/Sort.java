package com.sc.jysc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.cglib.core.GeneratorStrategy;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jackbing on 2018/1/2.
 */
@Entity
@Table(name = "sort")
@ApiModel("分类信息")
public class Sort {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @ApiModelProperty("逻辑Id")
    private String id;
    @ApiModelProperty("创建时间")
    private Long createtimestamp;
    @ApiModelProperty("修改时间")
    private Long modifytimestamp;
    @ApiModelProperty("产品分类编号")
    private String sortId;
    @ApiModelProperty("产品分类名称")
    private String sortName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatetimestamp() {
        return createtimestamp;
    }

    public void setCreatetimestamp(Long createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public Long getModifytimestamp() {
        return modifytimestamp;
    }

    public void setModifytimestamp(Long modifytimestamp) {
        this.modifytimestamp = modifytimestamp;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}
