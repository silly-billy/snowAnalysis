package com.sillybilly.loginmanager.dao;

import com.sillybilly.loginmanager.domain.TSaRolePermissionRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sillybilly.loginmanager.domain.bo.RoleAndPermissionInfoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-09
 */
public interface TSaRolePermissionRelationMapper extends BaseMapper<TSaRolePermissionRelation> {

    /**
     * 根据角色id批量查询权限
     * @param ids
     * @return
     */
    List<RoleAndPermissionInfoBO> queryByRoleIds(@Param(value = "roleIds") Set<Integer> ids);

}
