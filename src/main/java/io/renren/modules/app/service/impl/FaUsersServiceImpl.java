package io.renren.modules.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.app.dao.FaUsersMapper;
import io.renren.modules.app.entity.FaUsers;
import io.renren.modules.app.service.IFaUsersService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Yww
 * @since 2020-11-14
 */
@Service
public class FaUsersServiceImpl extends ServiceImpl<FaUsersMapper, FaUsers> implements IFaUsersService {

}
