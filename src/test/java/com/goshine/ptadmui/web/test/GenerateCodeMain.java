package com.goshine.ptadmui.web.test;

import org.mybatis.generator.api.ShellRunner;
/**
 * 代码生成器
 * @author baisj
 */
public class GenerateCodeMain {
	  public static void main(String[] args){
	       args=new String[]{"-configfile", "src\\main\\resources\\generatorConfig.xml", "-overwrite" };
	       ShellRunner.main(args);
	  }
}
