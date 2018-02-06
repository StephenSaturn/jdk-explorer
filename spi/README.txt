SPI(Service Provider Interface), JDK内置的一种服务提供发现机制

它是一种动态替换发现的机制，比如说，有个接口，想运行时动态的给它添加实现，
只需要提供这样一个实现，而后，把新加的实现"描述"给JDK就OK了

描述是指在src root目录下一定有个"META-INF/services/xx.yy.zz.Interface"这样目录的文件（JDK写死了）

文件名接口的完整路径名

alibaba开源的Dubbo就是基于SPI机制提供扩展功能

