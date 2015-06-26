/**
 * created since 2012-5-23
 */
package com.gztpay.test.bootstrap;


import com.gztpay.test.bootstrap.tomcat.TomcatBootstrapHelper;

public class JavaTestTomcatBootStrap {
 
	public static void main(String[] args) throws Exception {
		new TomcatBootstrapHelper(8080,false, "dev").start();
	}
}
