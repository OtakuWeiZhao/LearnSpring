package test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.ExampleBean;

public class TestCase {
	/**
	 * 测试实例化Spring容器
	 */
	@Test
	public void testInitContext() {
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
	}

	/**
	 * 测试Spring支持多种JavaBean对象创建方式
	 */
	@Test
	public void testCreateBeanObject() {
		// 实例化Spring容器
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);

		// 1.用构造器实例化
		// 利用Spring调用构造器GregorianCalendar创建Calendar实例
		// Calendar call = (Calendar) ac.getBean("calendarObj1"); //方式1
		Calendar cal1 = ac.getBean("calendarObj1", Calendar.class);
		System.out.println("cal1:" + cal1);

		// 2.使用静态工厂方法实例化
		// 利用Spring调用Calendar的静态工厂方法getInstance()创建Calendar实例
		Calendar cal2 = ac.getBean("calendarObj2", Calendar.class);
		System.out.println("cal2:" + cal2);

		// 3.使用实例工厂方法实例化
		// 利用Spring创建GregorianCalendar对象作为工厂,调用getTime()方法创建
		// Date类型对象实例
		Date date = ac.getBean("dateObj", Date.class);
		System.out.println("date:" + date);
	}

	@Test
	public void testExampleBean() {
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		// 获取ExampleBean对象
		ExampleBean bean1 = ac.getBean("exampleBean", ExampleBean.class);
		ExampleBean bean2 = ac.getBean("exampleBean", ExampleBean.class);
		// 若Bean作用域scope为空(默认为单例模式singleton),ExampleBean只被调用
		// 一次,创建过一个对象,比较操作符"=="输出为true,说明Spring容器创建Bean
		// 对象时唯一实例,是单例对象
		// 若修改scope属性为(原型模式)prototype,则控制台输出如下,
		// 创建的bean不再是单例模式了
		System.out.println(bean1 == bean2);
		// 关闭Spring容器, 注意AbstractApplicationContext类型定义了 close()方法
		AbstractApplicationContext ctx = (AbstractApplicationContext) ac;
		// 控制台没有输出"销毁ExampleBean对象"的结果,因为在配置文件中destroy-method
		// 属性仅仅对单例模式起作用,在prototype模式下没有意义
		// 由于ExampleBean依赖于ExampleBean1,因此在创建ExampleBean的同时,
		// 也创建了ExampleBean1
		ctx.close();
	}
}
