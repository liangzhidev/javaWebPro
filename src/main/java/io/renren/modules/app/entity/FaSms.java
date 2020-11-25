package io.renren.modules.app.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 短信验证码表
 * </p>
 *
 * @author Yww
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FaSms implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 事件
     */
    private String event;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证次数
     */
    private Integer times;

    /**
     * IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private Integer createtime;


}
