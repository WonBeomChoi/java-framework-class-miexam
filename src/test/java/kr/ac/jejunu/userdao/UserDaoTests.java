package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "헐크";
        String password = "1111";
        UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User rUser = userDao.get(id);

        assertThat(rUser.getId(), is(id));
        assertThat(rUser.getName(), is(name));
        assertThat(rUser.getPassword(), is(password));
    }@Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "헐크";
        String password = "1111";
        UserDao userDao = new UserDao(new HallaConntionMaker());
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
