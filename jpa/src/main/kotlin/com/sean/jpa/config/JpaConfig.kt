package com.sean.jpa.config

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource
import kotlin.collections.HashMap

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.sean"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class JpaConfig(
    private val env: Environment
) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun factoryDataSource(): DataSource {
        return DataSourceBuilder.create().apply {
            if(env.getProperty("spring.datasource.driverClassName").isNullOrBlank()) {
                driverClassName("com.mysql.cj.jdbc.Driver")
            }
        }.build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = factoryDataSource()
            setPackagesToScan("com.sean")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            jpaPropertyMap.putAll(properties())
        }

    @Bean
    fun transactionManager() = JpaTransactionManager(entityManagerFactory().`object`!!)

    private fun properties(): HashMap<String, Any> {
        var prop = HashMap<String, Any>()
        var ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto")
        if(ddlAuto.isNullOrBlank() || ddlAuto.contains("create", ignoreCase = true)) {
            ddlAuto = "update"
        }
        prop["hibernate.hbm2ddl.auto"] = ddlAuto as Any
        var dialect = env.getProperty("hibernate.dialect")
        if(dialect.isNullOrBlank()) {
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
        }
//        prop["hibernate.dialect"] = dialect as Any
        prop["spring.jpa.database-platform"] = dialect as Any
        prop["hibernate.physical_naming_strategy"] =
            CamelCaseToUnderscoresNamingStrategy::class.java.name as Any
        prop["hibernate.implicit_naming_strategy"] =
            SpringImplicitNamingStrategy::class.java.name as Any
        var lazyLoad = env.getProperty("hibernate.enable_lazy_load_no_trans")
        if(lazyLoad.isNullOrBlank()) {
            lazyLoad = "false"
        }
        prop["hibernate.enable_lazy_load_no_trans"] = lazyLoad as Any
        return prop
    }

    /*
    private fun properties(): HashMap<String, Object> {
        var prop = HashMap<String, Object>()
        var ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto")
        if(ddlAuto.isNullOrBlank() || ddlAuto.contains("create", ignoreCase = true)) {
            ddlAuto = "update"
        }
        prop["hibernate.hbm2ddl.auto"] = ddlAuto as Object
        var dialect = env.getProperty("hibernate.dialect")
        if(dialect.isNullOrBlank()) {
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
        }
        prop["spring.jpa.database-platform"] = dialect as Object
        prop["hibernate.physical_naming_strategy"] =
            CamelCaseToUnderscoresNamingStrategy::class.java.name as Object
        prop["hibernate.implicit_naming_strategy"] =
            SpringImplicitNamingStrategy::class.java.name as Object
        var lazyLoad = env.getProperty("hibernate.enable_lazy_load_no_trans")
        if(lazyLoad.isNullOrBlank()) {
            lazyLoad = "false"
        }
        prop["hibernate.enable_lazy_load_no_trans"] = lazyLoad as Object
        return prop
    }
     */

}