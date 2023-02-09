# 组件版本

* GraalVM 22.3.1
* JDK 17
* Spring Boot 3
* MyBatis Spring Boot 3.0.1-SNAPSHOT

# 出现过的问题

## Accessing an URL protocol that was not enabled. The URL protocol http is supported but not enabled by default.

```text
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'myMapper': org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: java.net.MalformedURLException: Accessing an URL protocol that was not enabled. The URL protocol http is supported but not enabled by default. It must be enabled by adding the --enable-url-protocols=http option to the native-image command.
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1751) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1405) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1325) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.aot.AutowiredFieldValueResolver.resolveValue(AutowiredFieldValueResolver.java:189) ~[na:na]
        ... 21 common frames omitted
```

* 解决方法

buildArgs 处添加 --enable-url-protocols=http

```xml
      <plugin>
        <groupId>org.graalvm.buildtools</groupId>
        <artifactId>native-maven-plugin</artifactId>
          <configuration>
            <buildArgs>
                --enable-url-protocols=http
            </buildArgs>
        </configuration>
      </plugin>
```

##  Failed to read external DTD 'mybatis-3-mapper.dtd'

```text
org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 2; columnNumber: 108; External DTD: Failed to read external DTD 'mybatis-3-mapper.dtd', because 'http' access is not allowed due to restriction set by the accessExternalDTD property.
        at org.apache.ibatis.parsing.XPathParser.createDocument(XPathParser.java:263) ~[na:na]
        at org.apache.ibatis.parsing.XPathParser.<init>(XPathParser.java:127) ~[na:na]
        at org.apache.ibatis.builder.xml.XMLMapperBuilder.<init>(XMLMapperBuilder.java:81) ~[na:na]
        at org.apache.ibatis.builder.xml.XMLMapperBuilder.<init>(XMLMapperBuilder.java:76) ~[na:na]
        at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.loadXmlResource(MapperAnnotationBuilder.java:178) ~[na:na]
        at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.parse(MapperAnnotationBuilder.java:118) ~[na:na]        at org.apache.ibatis.binding.MapperRegistry.addMapper(MapperRegistry.java:72) ~[na:na]
        at org.apache.ibatis.session.Configuration.addMapper(Configuration.java:872) ~[mybatis-native-demo:3.5.11]
        at org.mybatis.spring.mapper.MapperFactoryBean.checkDaoConfig(MapperFactoryBean.java:80) ~[mybatis-native-demo:3.0.1]
        at org.springframework.dao.support.DaoSupport.afterPropertiesSet(DaoSupport.java:44) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1797) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1747) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1405) ~[mybatis-native-demo:6.0.2]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1325) ~[mybatis-native-demo:6.0.2]
```

* 解决方法

启动时添加jvm参数

```shell
./mybatis-native-demo -Djavax.xml.accessExternalDTD=all
```