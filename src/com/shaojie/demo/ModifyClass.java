package com.shaojie.demo;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ModifyClass {

	public static void main(String[] args) {
		updateMethod();
	}
	
	public static void updateMethod(){
		try {
			ClassPool cPool = new ClassPool(true);
		        //如果该文件引入了其它类，需要利用类似如下方式声明
			//cPool.importPackage("java.util.List");
			/**
			 private final i vi;
  public final boolean vk;
  public final boolean vl;
  public final boolean vm;
  public final boolean vn;
  public final boolean vo;
  public final boolean vp;
  public final boolean vq;
  public final f vr;
  public final f vs;
  public final Set vt;
  public final boolean vu = false;
			 */
			
			//设置class文件的位置
			cPool.insertClassPath("/home/shaojie/studio3t/data-man-mongodb-ent-2018.4.2.jar");
			
			//获取该class对象
			CtClass cClass = cPool.get("t3.common.lic.a.i");
			
			//获取到对应的方法
			CtMethod cMethod = cClass.getDeclaredMethod("L");
			
			//更改该方法的内部实现
			//需要注意的是对于参数的引用要以$开始，不能直接输入参数名称
			cMethod.setBody("{ return ; }");
			
			cMethod = cClass.getDeclaredMethod("aK");
			cMethod.setBody("{ return ; }");
			
			//替换原有的文件
			cClass.writeFile("/home/shaojie/desktop");
			
			System.out.println("=======修改方法完=========");
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
