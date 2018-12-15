package com.cs.springbootestfour;



import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;


@SpringBootApplication
public class SpringbootestfourApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootestfourApplication.class, args);
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            // WebAppResourceLoader 配置root路径是关键
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource("classpath:/templates").getFile().getPath());
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration){
        BeetlSpringViewResolver beetlSpringViewResolver=new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return  beetlSpringViewResolver;
    }

    //配置包扫描
    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.cs.springbootestfour.dao");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }


        @Bean(name = "sqlManagerFactoryBean")
        @Primary
        public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datesource") DataSource dataSource){
            SqlManagerFactoryBean factoryBean=new SqlManagerFactoryBean();
            BeetlSqlDataSource source=new BeetlSqlDataSource();
            source.setMasterSource(dataSource);
            factoryBean.setCs(source);
            factoryBean.setDbStyle(new MySqlStyle());
            factoryBean.setInterceptors(new Interceptor[]{new DebugInterceptor()});
            factoryBean.setNc(new UnderlinedNameConversion());
            factoryBean.setSqlLoader(new ClasspathLoader("/sql"));
            return factoryBean;

        }
    //配置数据库
    @Bean(name ="datesource")
    public  DataSource getDataSource(){
        return  DataSourceBuilder.create().url("jdbc:mysql:///wrod").username("root").password("root").build();
    }

    @Bean(name = "txManager")
    public  DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datesource") DataSource dataSource){
        DataSourceTransactionManager dsm=new DataSourceTransactionManager();
        dsm.setDataSource(dataSource);
        return dsm;
    }


}

