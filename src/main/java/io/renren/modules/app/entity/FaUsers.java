package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import org.apache.commons.io.filefilter.FalseFileFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Yww
 * @since 2020-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FaUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 身份类别:1=个人,2=企业
     */
    private String leibie;
    
    /**
     * 企业身份类型
     */
    private String qyLevel;

    /**
     * 名称
     */
    private String name;

    /**
     * 证件号码/营业执照号
     */
    private String idNum;

    /**
     * 证件地址
     */
    private String address;

    /**
     * 电话
     */
    private String tel;

    /**
     * 营业执照图片
     */
    private String image;

    /**
     * 状态:0=正常,1=锁定
     */
    private String status;

    /**
     * 创建时间
     */
    private Integer createtime;

    /**
     * 更新时间
     */
    private Integer updatetime;

    /**
     * 图片base64
     */
    private String base64;

    /**
     * 密码
     */
    private String password;

    /**
     * 签名图片
     */
    private String signatureImage;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 官方网站
     */
    private String weburl;

    /**
     * 经办人名称
     */
    private String jbrName;

    /**
     * 经办人证件号码
     */
    private String jbrCode;

    /**
     * 公司图片1
     */
    private String company1Image;

    /**
     * 公司图片2
     */
    private String company2Image;

    /**
     * 公司图片3
     */
    private String company3Image;

    /**
     * 公司图片4
     */
    private String company4Image;

    /**
     * 生产地址 设备注册显示设备地址，小程序注册或绑定后为小程序地址
     */
    private String productionaddress;

    /**
     * 地址 省ID 小程序适用
     */
    private Integer provinceId;

    /**
     * 地址 市ID 小程序适用
     */
    private Integer cityId;

    /**
     * 地址 区县ID 小程序适用
     */
    private Integer districtId;

    /**
     * 地址 乡镇ID 小程序适用
     */
    private Integer townId;

    /**
     * 注册方式:1=默认设备注册,2=小程序注册
     */
    private String regWay;

    /**
     * 企业简介
     */
    private String about;

    /**
     * 公司荣誉图片1
     */
    private String honor1Image;

    /**
     * 公司荣誉图片2
     */
    private String honor2Image;

    /**
     * 公司荣誉图片3
     */
    private String honor3Image;

    /**
     * 公司荣誉图片4
     */
    private String honor4Image;

    /**
     * 蓝牙打印机mac地址
     */
    private String bluetoothMac;

    /**
     * 蓝牙纸张尺寸:1=默认,2=大尺寸
     */
    private String bluetoothType;
    /**
     * 注册时发送的手机验证码
     */
    @TableField(exist = false)
    private String code;
    /**
     * 1.密码登录
     * 2.验证码登录
     */
    @TableField(exist = false)
    private int type;


}
