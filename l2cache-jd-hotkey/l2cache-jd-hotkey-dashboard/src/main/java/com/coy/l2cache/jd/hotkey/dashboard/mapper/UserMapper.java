package com.coy.l2cache.jd.hotkey.dashboard.mapper;

import com.coy.l2cache.jd.hotkey.dashboard.common.domain.req.SearchReq;
import com.coy.l2cache.jd.hotkey.dashboard.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPk(User record);

    List<User> listUser(SearchReq param);

    User findByNameAndPwd(User user);

    List<String> listApp();

    List<User> selectHkUserList(User user);

    User selectByUserName(String userName);
}