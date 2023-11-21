package ra.academy.configs;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[]{WebConfig.class};
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[0];
   }

   @Override
   protected String[] getServletMappings() {
      return new String[]{"/"};
   }

   @Override
   protected Filter[] getServletFilters() {
      CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
      encodingFilter.setForceEncoding(true);
      encodingFilter.setEncoding("utf-8");
      return new Filter[]{encodingFilter};
   }
}
