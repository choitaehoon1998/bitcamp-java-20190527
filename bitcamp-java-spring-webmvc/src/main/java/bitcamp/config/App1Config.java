package bitcamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@ComponentScan("bitcamp.app1")
public class App1Config {
  
  
  @Bean
  public MultipartResolver multipartResolver() {
    //스프링 방식으로 파일 업로드를 처리하고싶다면, 
    //이 메서드를 통해 MultipartResolver 구현체를 생성해야한다 .
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(1000000);
    mr.setMaxInMemorySize(200000);
    mr.setMaxUploadSizePerFile(500000);
    return mr;
  }
}
