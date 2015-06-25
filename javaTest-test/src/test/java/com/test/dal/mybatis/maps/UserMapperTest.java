package com.test.dal.mybatis.maps;

import com.gzt.test.base.BaseSpringContextTest;
import com.test.dal.mybatis.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zyinyan on 2015/6/25.
 */
public class UserMapperTest extends BaseSpringContextTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setName("zyinyan");
        user.setAge(18);
        userMapper.insertSelective(user);
    }
}
