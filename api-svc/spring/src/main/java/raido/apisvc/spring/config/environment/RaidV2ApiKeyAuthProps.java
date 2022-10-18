package raido.apisvc.spring.config.environment;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import raido.apisvc.util.Guard;
import raido.apisvc.util.Log;

import java.util.Arrays;

import static raido.apisvc.util.Log.to;
import static raido.apisvc.util.StringUtil.mask;

@Component
public class RaidV2ApiKeyAuthProps {
  private static final Log log = to(RaidV2ApiKeyAuthProps.class);
  
  /** spring el uses `,` as separator, so secrets can't contain them.
   Maybe use horizontal tab or something as separator? */
  @Value("${RaidV2ApiKeyAuth.jwtSecrets}")
  private String[] jwtSecrets;

  @Value("${RaidV2ApiKeyAuth.issuer:https://localhost:8080}")
  public String issuer;
  
  public Algorithm signingAlgo;
  public JWTVerifier[] verifiers;
  
  @PostConstruct
  public void init(){
    Guard.hasValue("jwtSecrets", jwtSecrets);
    Guard.hasValue("issuer", issuer);
    var signingSecret = jwtSecrets[0].trim();
    // for SHA256 to be secure, need to have a min level of entropy
    // https://auth0.com/blog/brute-forcing-hs256-is-possible-the-importance-of-using-strong-keys-to-sign-jwts/
    Guard.isTrue("signingSecret is too short", signingSecret.length() >= 32 );
    log.with("size", jwtSecrets.length).
      with("signingSecret", mask(signingSecret)).
      info("jwtSecrets");

    this.signingAlgo = Algorithm.HMAC256(signingSecret);
    
    // Javadoc says JWTVerifier is thread safe
    verifiers = Arrays.stream(jwtSecrets).map(i->
      JWT.require(Algorithm.HMAC256(i)).
        withIssuer(this.issuer).
        build() 
      ).toArray(JWTVerifier[]::new);
  }
}