package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTests {
    private UserDao userDao;
    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
       userDao = applicationContext.getBean("getUserDao", UserDao.class);
    }
    @Test
    public void testGet() throws SQLException{
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void testAdd() throws SQLException{
        String name = "헐크";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User rUser = userDao.get(id);

        assertThat(rUser.getId(), is(id));
        assertThat(rUser.getName(), is(name));
        assertThat(rUser.getPassword(), is(password));
    }
    @Test
    public void testUpdate() throws SQLException {
        String name = "헐크";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        String cName = "바뀜";
        String cPassword ="1234";
        User cUser = userDao.get(id);
        Long cId = cUser.getId();
        cUser.setName(cName);
        cUser.setPassword(cPassword);
        userDao.update(cUser);
        assertThat(cUser.getId(), is(cId));
        assertThat(cUser.getPassword(), is(cPassword));
        assertThat(cUser.getName(), is(cName));


    }
    @Test
    public void testDelete() throws SQLException {
        String name = "헐크";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        user = userDao.get(id);

        assertThat(user, nullValue());
    }
//    @Test
//    public void testHallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1l;
//        String name = "헐크";
//        String password = "1111";
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
}
