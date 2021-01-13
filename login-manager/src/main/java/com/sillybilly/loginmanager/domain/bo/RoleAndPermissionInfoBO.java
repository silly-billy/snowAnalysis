package com.sillybilly.loginmanager.domain.bo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleAndPermissionInfoBO {

    /**
     * role主键
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 主键
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
