package com.sc.jysc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jackbing on 2018/1/2.
 */
@ApiModel("产品信息")
@Entity
@Table(name = "t_bas_product")
public class Product implements Serializable{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @ApiModelProperty("逻辑Id")
    private String id;
    @ApiModelProperty("创建时间")
    private Long createtimestamp;
    @ApiModelProperty("修改时间")
    private Long modifytimestamp;

    @ApiModelProperty("产品编号")
    private String prodId;
    @ApiModelProperty("产品名称")
    private String prodName;
    @ApiModelProperty("销售数量")
    private int saleCount;
    @ApiModelProperty("图片路径")
    private String image;
    @ApiModelProperty("成本价格")
    private BigDecimal price;
    @ApiModelProperty("销售价格")
    private BigDecimal salePrice;
    @ApiModelProperty("产品介绍")
    private String descript;
    @ApiModelProperty("销售时间")
    private Date saleDate;
    @ApiModelProperty("产品分类编号外键")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="sortId" )
    private Sort sort;

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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
