package entity;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class DatabaseUtil {
	  //定义变量
    Configuration config;
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    //before表示在方法执行前执行
    @Before
    public void setUp()
    {
      //1.加载hibernate.cfg.xml配置
      config=new Configuration().configure();
      //2.获取SessionFactory
      sessionFactory=config.buildSessionFactory(); 
     //3.获得一个session
      session=sessionFactory.openSession();
      //4.开始事务
      transaction=session.beginTransaction();
    }
    //添加操作
    
    @Test
    public void insert()
    {   
    	
        config=new Configuration().configure();
        //2.获取SessionFactory
        sessionFactory=config.buildSessionFactory(); 
       //3.获得一个session
        session=sessionFactory.openSession();
        //4.开始事务
        transaction=session.beginTransaction();
      //5.操作
      Transactions ts = new Transactions();
     //customer.setId(1);
      ts.setAccount("0x222");
      ts.setTransCate("test2");
      ts.setTransHash("0x68656C6C6F20776F726C6468656C6C6F20776F726C6468656C6C6F20776F726C64");
      session.save(ts);
      //6.提交事务
      transaction.commit();
      //7.关闭资源
      session.close();
      sessionFactory.close();
    }
    
    public void insert(String from,String transHash)
    {   
    	
        config=new Configuration().configure();
        //2.获取SessionFactory
        sessionFactory=config.buildSessionFactory(); 
       //3.获得一个session
        session=sessionFactory.openSession();
        //4.开始事务
        transaction=session.beginTransaction();
      //5.操作
      Transactions ts = new Transactions();
     //customer.setId(1);
      ts.setAccount(from);
      ts.setTransCate("test2");
      ts.setTransHash(transHash);
      session.save(ts);
      //6.提交事务
      transaction.commit();
      //7.关闭资源
      session.close();
      sessionFactory.close();
    }
    
    @Test
    public void fun2(){
    	String from = "0x46eda370dd2615a63216f701f656e5562913326d";
    	String transHash = "0x2b59258651f9104002edc183cf43fb421426ea9a7692f33eae2d9ee1add5e86b";
    	insert(from,transHash);
    }
    
    public void insert(Transactions ts){
    	//session.save(ts);
    	 config=new Configuration().configure();
         //2.获取SessionFactory
         sessionFactory=config.buildSessionFactory(); 
        //3.获得一个session
         session=sessionFactory.openSession();
         //4.开始事务
         transaction=session.beginTransaction();
       //5.操作
      // Transactions ts = new Transactions();
      //customer.setId(1);

       session.save(ts);
       //6.提交事务
       transaction.commit();
       //7.关闭资源
       session.close();
       sessionFactory.close();
    }
    
    
    public List getTrans(String account){
    	setUp();
    	Criteria criteria = session.createCriteria(Transactions.class);
    	List<Transactions> list = criteria.add(Restrictions.eq("account", account)).list();
    	for (Object object : list) {
			System.out.println(object);
		}
    	closeTransaction();
    	return list;
    }
    
    @Test
    public void fun(){
    	List<Transactions> list = getTrans("0x111");
/*    	for (Transactions transactions : list) {
			System.out.println(transactions);
		}*/
    }
  /*  //删除操作
    @Test
     public void delete()
     {
        //先查询
        Customer customer=(Customer)session.get(Customer.class, 1);
        //再删除
        session.delete(customer);
     }
    //查询操作
    @Test
    public void select()
    {
        Customer customer=(Customer)session.get(Customer.class, 1);
        System.out.println(customer);  
    }
    //更新操作
    @Test
    public void update()
    {   
      Customer customer=new Customer();
      customer.setId(1);
      customer.setName("zhangsan");
      customer.setAge(20);
      customer.setSex("m");
      //修改地址为beijing
      customer.setCity("beijing");
      //存在就更新，不存在就执行插入操作
      session.saveOrUpdate(customer);
    }*/
    //After表示在方法执行结束后执行
    @After
    public void closeTransaction()
    {
      //6.提交事务
      transaction.commit();
      //7.关闭资源
      session.close();
      sessionFactory.close();
    }
    
    
    public static void main(String[] args) {
		new DatabaseUtil().insert(new Transactions());
	}
    
    
}
