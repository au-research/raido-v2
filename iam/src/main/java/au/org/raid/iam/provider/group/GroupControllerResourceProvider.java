package au.org.raid.iam.provider.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.ext.Provider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resource.RealmResourceProvider;
@Provider
public class GroupControllerResourceProvider implements RealmResourceProvider {
    private KeycloakSession session;
    private ObjectMapper objectMapper;

    public GroupControllerResourceProvider(final KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return new GroupController(session);
    }

    @Override
    public void close() {

    }
}
