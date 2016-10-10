package com.lynas.config

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect
import nz.net.ultraq.thymeleaf.LayoutDialect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.web.servlet.config.annotation.*
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import java.util.*

@Configuration
@EnableWebMvc
@ComponentScan("com.lynas")
open class WebConfig() : WebMvcConfigurerAdapter() {

    // Tried this it does not work
    //open class WebConfig @Autowired constructor(val interceptorConfig: InterceptorConfig) : WebMvcConfigurerAdapter() {

    @Autowired
    lateinit var interceptorConfig:InterceptorConfig


    @Bean
    open fun templateResolver(): SpringResourceTemplateResolver {
        val resolver = SpringResourceTemplateResolver().apply {
            isCacheable = false
            templateMode = TemplateMode.HTML
            prefix = "/"
            suffix = ".html"
        }
        return resolver

    }

    @Bean
    open fun templateEngine(): SpringTemplateEngine {

        return SpringTemplateEngine().apply {
            setTemplateResolver(templateResolver())
            setAdditionalDialects(HashSet<IDialect>().apply {
                add(LayoutDialect())
            })
            addDialect(DataAttributeDialect())
        }
    }

    @Bean
    open fun viewResolver() = ThymeleafViewResolver().apply {
        templateEngine = templateEngine()
        order = 1
        viewNames = arrayOf("*", "js/*", "template/*")
    }

    @Bean
    open fun properties() = PropertySourcesPlaceholderConfigurer().apply {
        setIgnoreResourceNotFound(true)
        setIgnoreUnresolvablePlaceholders(false)
    }


    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        //registry.addInterceptor(InterceptorConfig())
        registry.addInterceptor(interceptorConfig)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }
}

















