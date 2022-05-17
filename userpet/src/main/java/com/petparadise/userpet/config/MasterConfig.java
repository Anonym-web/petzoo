package com.petparadise.userpet.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.DruidPasswordCallback;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 王培忠
 * @date 2020/8/25 11:00
 * @email 805705089@qq.com
 * @Description
 * @Reason ADDREASON
 * @since JDK 1.8
 */
@Configuration
@MapperScan(basePackages ="com.petparadise.userpet.mapper", sqlSessionTemplateRef  = "masterSqlSessionTemplate")
public class MasterConfig {

	@Value("${spring.datasource.master.url}")
	private String dbUrl;
	@Value("${spring.datasource.master.username}")
	private String userName;
	@Value("${spring.datasource.master.password}")
	private String passWord;
	@Value("${spring.datasource.master.type}")
	private String type;
	/*@Value("${spring.datasource.publickey}")
	private String publicKey;*/
	
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource testDataSource() {
    	DruidDataSource dataSource=new DruidDataSource();
		try {
//			String dbPassWord = ConfigTools.decrypt(publicKey, passWord);
			DruidPasswordCallback druidPasswordCallback=new DruidPasswordCallback();
//			druidPasswordCallback.setPassword(dbPassWord.toCharArray());
			dataSource.setUrl(dbUrl);
			dataSource.setUsername(userName);
			dataSource.setPassword(passWord);
			dataSource.setDbType(type);
			dataSource.setPasswordCallback(druidPasswordCallback);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return dataSource;    }
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//		bean.setConfigLocation(new ClassPathResource("Configuration.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/petparadise/userpet/mapper/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}