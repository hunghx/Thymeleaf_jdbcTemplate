package ra.academy.configs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import ra.academy.dao.StudentDao;

import javax.sql.DataSource;
import java.util.ResourceBundle;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra.academy.controllers"})
public class WebConfig implements WebMvcConfigurer , ApplicationContextAware {
   private ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
   }

   // cấu hình view resolver
//Cấu hình Thymleaf
   @Bean
   public SpringResourceTemplateResolver templateResolver() {
      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
      templateResolver.setApplicationContext(applicationContext);
      templateResolver.setPrefix("/WEB-INF/views/");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      templateResolver.setCharacterEncoding("UTF-8");
      return templateResolver;
   }

   @Bean
   public SpringTemplateEngine templateEngine() {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver());
      return templateEngine;
   }

   @Bean
   public ThymeleafViewResolver viewResolver() {
      ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
      viewResolver.setTemplateEngine(templateEngine());
      viewResolver.setCharacterEncoding("UTF-8");
      viewResolver.setContentType("UTF-8");
      return viewResolver;
   }

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/css/**","/js/**","/img/**")
              .addResourceLocations("/assets/css/","/assets/js/","/assets/img/");
   }


   // cáu hình bean resource
   @Bean
   public MessageSource messageSource(){
      ResourceBundleMessageSource message = new ResourceBundleMessageSource();
      message.setDefaultEncoding("utf-8");
      message.setBasename("message");
      return message;
   }
   // bean cấu hình keets nối db
   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource db= new DriverManagerDataSource();
      db.setDriverClassName("com.mysql.cj.jdbc.Driver");
      db.setUrl("jdbc:mysql://localhost:3306/qlsv");
      db.setUsername("root");
      db.setPassword("18061999");
      return db;
   }
   // cấu hình bean jdbc template
   @Bean
   public JdbcTemplate jdbcTemplate(){
      return new JdbcTemplate(dataSource());
   }
   @Bean
   public StudentDao studentDao(){
      StudentDao st =new StudentDao();
      st.setJdbcTemplate(jdbcTemplate());
      return st;
   }

}
