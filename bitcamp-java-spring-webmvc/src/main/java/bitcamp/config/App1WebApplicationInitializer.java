package bitcamp.config;

import java.io.File;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// WebApplicationInitializer 구현체를 만드는 세 번째 방법
// => 인터페이스를 직접 구현하는 대신에 그 인터페이스를 구현한 
//    AbstractDispatcherServletInitializer 클래스를 상속 받기
// 
//
public class App1WebApplicationInitializer 
extends AbstractAnnotationConfigDispatcherServletInitializer {
  String uploadTmpDir;
  
  public App1WebApplicationInitializer() {
    uploadTmpDir = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
    System.out.println("업로드 임시 폴더" + uploadTmpDir);
  }
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }
  
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {App1Config.class};
  }
  
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app1/*"};
  }
  
  @Override
  protected String getServletName() {
    return "app1";
  }

  
//  @Override
//  protected void customizeRegistration(Dynamic registration) {
//    System.getProperty("java.io.tmpdir");
//      // Servlet 3.0 API의 파일업로드를 사용하려면 다음과 같이 
//      // DispatcherServlet에 설정을 추가해야한다 
//      // 만약 Spring의 방식으로 파일업로드를 처리하고싶다면 
//      // AppConfig.java의 MultiPartResolver 를 확인하라 
//        //DisPatcherServlet 이 Multipart/form-data 으로 전송된 데이터를 처리하려면
//    // 해당 컴포넌트를 등록해야한다 . 
//      registration.setMultipartConfig(new MultipartConfigElement(
//          uploadTmpDir, //업로드할 파일을 임시 보관할 위치 
//          1000000, // 최대 업로드할수있는 파일들의 총크기 
//          1500000, // 요청 전체 데이터의 크기 
//          200000 //업로드 되고있는 파일을 메모리에 임시 보관하는 크기 . 
//          ));
//  }
  
  
  
  
  
  
  
  
}
