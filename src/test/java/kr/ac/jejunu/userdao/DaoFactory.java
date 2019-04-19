package kr.ac.jejunu.userdao;


public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnetionMaker());
    }
    private ConnectionMaker getConnetionMaker() {
        return new JejuConnectionMaker();
    }
    }
