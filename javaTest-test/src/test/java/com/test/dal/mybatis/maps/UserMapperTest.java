package com.test.dal.mybatis.maps;

import com.gzt.test.base.BaseSpringContextTest;
import com.test.dal.mybatis.model.User;
import com.test.dal.mybatis.model.UserExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testSelectOnlyOneByExample() throws Exception {
        try {
            UserExample userExample = new UserExample();
            userExample.or().andNameEqualTo("zyinyan");
            List<User> users = userMapper.selectByExample(userExample);
            assertEquals(new Integer(18),users.get(0).getAge());
        } catch (Exception e) {
            logger.error("{}",e);
        }
    }
}
