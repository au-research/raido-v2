package raido.functional;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;
import raido.functional.config.FuncTestProps;
import raido.functional.config.FunctionalTestConfig;
import raido.util.Log;

import static raido.util.Log.to;
import static raido.util.RestUtil.createEntityWithBearer;

@SpringJUnitConfig(FunctionalTestConfig.class)
public abstract class FunctionalTestCase {
  private static final Log log = to(FunctionalTestCase.class);
  
  @Autowired protected RestTemplate rest;
  @Autowired protected FuncTestProps props;


  @RegisterExtension
  protected static JettyTestServer jettyTestServer =
    new JettyTestServer();  
  
  public <T> T get(String authnToken, String url, Class<T> resultType){
    HttpEntity<T> entity = createEntityWithBearer(authnToken);

    var epResponse = rest.exchange(
      raidoApiServerUrl(url),
      HttpMethod.GET, entity, resultType);
    
    return epResponse.getBody();
  }

  public <T> T anonGet(String url, Class<T> resultType){
    HttpEntity<T> entity = new HttpEntity<T>(new HttpHeaders());

    var epResponse = rest.exchange(
      raidoApiServerUrl(url),
      HttpMethod.GET, entity, resultType);
    
    return epResponse.getBody();
  }

  public <TRequest, TResult> TResult post(
    String authnToken, String url, 
    TRequest request, Class<TResult> resultType
  ){
    HttpEntity<TRequest> entity = createEntityWithBearer(authnToken, request);

    var epResponse = rest.exchange(
      raidoApiServerUrl(url),
      HttpMethod.POST, entity, resultType);
    
    return epResponse.getBody();
  }

  public String raidoApiServerUrl(String url){
    //noinspection HttpUrlsUsage
    return String.format("http://%s%s", props.raidoApiServer, url);
  }

  protected String getTestPrefix(TestInfo testInfo) {
    return testInfo.getTestClass().orElseThrow().getSimpleName();
  }
}