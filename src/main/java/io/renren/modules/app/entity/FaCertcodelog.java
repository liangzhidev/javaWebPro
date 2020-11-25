package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 合格证查询记录表
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FaCertcodelog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合格证扫码log表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 追朔码 关联合格证表
     */
    private String tracecode;

    /**
     * 扫码时间
     */
    private LocalDateTime createAt;

    /**
     * 设备登录id
     */
    private String loginId;

    /**
     * 地址ID
     */
    private Integer loginAddressId;

    /**
     * 扫码人员所属省市
     */
    private String provincesCity;


}
