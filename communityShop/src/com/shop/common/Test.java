package com.shop.common;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shop.bean.User;

public class Test {

	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 
	
    static{
        try{
            reader    = Resources.getResourceAsReader("mybatis_config.xml");
            System.out.println("reader "+reader);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("sqlSessionFactory "+sqlSessionFactory);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("session "+session);
        try {
        //ÃüÃû¿Õ¼ä+sqlµÄID
        User user = (User) session.selectOne("com.User.selectUserByID", 1);
        System.out.println(user.getUserAddress());
        System.out.println(user.getUserName());
        } finally {
        session.close();
        }
    }

}
