import com.lqs.dao.UserDao;
import com.lqs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private UserDao mapper;
    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("lqs");
        user.setId(20);
        mapper.insertOne(user);

    }

    @Test
    public void update(){
        User user = new User();
        user.setUsername("liqisong");
        user.setId(20);
        mapper.updateOne(user);
    }

    @Test
    public void delete(){
        mapper.deleteOne(20);
    }

    @Test
    public void selectAll(){
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void selectOne(){
        User user = mapper.findOne(10);
        System.out.println(user);
    }

    @Test
    public void findOneToOne(){
        List<User> userList = mapper.findUserAndRole();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
