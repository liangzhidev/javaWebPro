package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 合格证表
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FaCert implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 追溯码
     */
    private String tracecode;

    /**
     * 用户名称
     */
    private String usersName;

    /**
     * 证件号码
     */
    private String usersIdNum;

    /**
     * 证件地址
     */
    private String usersAddress;

    /**
     * 生产地址
     */
    private String productionAddress;

    /**
     * 电话
     */
    private String usersTel;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单位
     */
    private String productUnitName;

    /**
     * 产品数量
     */
    private Double proNumber;

    /**
     * 合格方式:1=自我承诺,2=内部质量控制,3=自我检测,4=委托第三方检测
     */
    private String way;

    /**
     * 介绍
     */
    private String description;

    /**
     * 图片1
     */
    private String url1Image;

    /**
     * 图片2
     */
    private String url2Image;

    /**
     * 图片3
     */
    private String url3Image;

    /**
     * 图片4
     */
    private String url4Image;

    /**
     * 图片5
     */
    private String url5Image;

    /**
     * 所有子追溯码
     */
    private String qrcode;

    /**
     * 打印数量
     */
    private Integer printNumber;

    /**
     * 创建时间
     */
    private Integer createtime;

    /**
     * 更新时间
     */
    private Integer updatetime;

    /**
     * 身份类别:1=个人,2=企业
     */
    private String leibie;

    /**
     * 经办人名称
     */
    private String jbrName;

    /**
     * 经办人证件号码
     */
    private String jbrCode;

    /**
     * 设备登录id
     */
    private String loginId;

    /**
     * 地址ID
     */
    private Integer loginAddressId;

    /**
     * 承诺:1=不使用禁限用农药兽药,0=否
     */
    private String chengnuo1;

    /**
     * 承诺:1=不使用非法添加物,0=否
     */
    private String chengnuo2;

    /**
     * 承诺:1=遵守农药安全间隔期、兽药休药期规定,0=否
     */
    private String chengnuo3;

    /**
     * 承诺:1=销售的食用农产品符合农药兽药残留食品安全国家标准,0=否
     */
    private String chengnuo4;

    /**
     * 打印类型0终端打印1印刷激活2蓝牙打印
     */
    private String printType;

    /**
     * 打印设备mac地址
     */
    private String mac;


}
