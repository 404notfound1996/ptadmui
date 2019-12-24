package com.goshine.ptadmui.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 服务启动时需要加载的一些内容示例，例如字典类
 * @author baisj
 */
@Component
@Order(1)//第1个执行，此为加载顺序，当有多个的时候，则可以设置优先级
public class InitRunner implements ApplicationRunner{
	private static Logger logger=LoggerFactory.getLogger(InitRunner.class);

	@Override
    public void run(ApplicationArguments applicationArguments) throws Exception{
         logger.debug("=============启动时加载示例内容开始=============");
         logger.debug("========见com.goshine.ptadmui.runner.InitRunner类========");
         //需要加载的内容代码逻辑，比如加载字典类到内存中
         logger.debug("=============启动时加载示例内容完毕=============");
    }
}
