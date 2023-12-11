package au.org.raid.api.service.datacite;

import au.org.raid.api.factory.DatacitePayloadFactory;
import au.org.raid.api.spring.config.DataciteProperties;
import au.org.raid.idl.raidv2.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataciteService {
    private final DataciteProperties properties;

    private String getDatacitePrefix(){
        return properties.getPrefix();
    }

    private String getDataciteSuffix(){
        // TODO: This is a temporary solution to create a random identifier for the datacite handle
        // This should be replaced with a proper solution
        return UUID.randomUUID().toString().split("-")[0];
    }

    public final String getDataciteHandle(){
        String prefix = getDatacitePrefix();
        String suffix = getDataciteSuffix();
        return prefix + "/" + suffix;
    }

    public DataciteMintResponse mintDataciteHandleContentPrefix(){
        String prefix = getDatacitePrefix();
        String suffix = getDataciteSuffix();

        DataciteMintResponse.Identifier.Property property = new DataciteMintResponse.Identifier.Property();
        property.index = 1;
        property.type = "DESC";
        property.value = "RAID handle";

        DataciteMintResponse.Identifier id = new DataciteMintResponse.Identifier();
        id.property = property;
        id.handle = prefix + "/" + suffix;

        DataciteMintResponse.Message message = new DataciteMintResponse.Message();
        message.type = null;
        message.value = null;

        DataciteMintResponse response = new DataciteMintResponse();
        response.setType("success");
        response.setIdentifier(id);
        response.setMessage(null);
        response.setTimestamp(LocalDateTime.now());

        return response;

    }

    public final String createDataciteRaid(RaidCreateRequest request, String handle) {
        // TODO: Use RestTemplate instead of HttpURLConnection
        String responseHandle;
        try {

            URL url = new URL("https://api.test.datacite.org/dois");
//            URL url = new URL(properties.getEndpoint() + "/dois");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            // Prepare the basic auth credentials
            String username = properties.getUser();
            String password = properties.getPassword();
            String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            httpURLConnection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            DatacitePayloadFactory payloadFactory = new DatacitePayloadFactory();
            String createPayload = payloadFactory.payloadForCreate(request, handle);

            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = createPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                ObjectMapper inputStreamObjectMapper = new ObjectMapper();
                JsonNode inputStreamRootNode = inputStreamObjectMapper.readTree(inputStream);
                responseHandle = inputStreamRootNode.path("data").path("id").asText();
            } catch (IOException e) {
                throw new RuntimeException("Error reading response");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error creating handle");
        }

        return responseHandle;
    }

    public final String updateDataciteRaid(RaidUpdateRequest request, String handle) {
        // TODO: Use RestTemplate instead of HttpURLConnection
        try {
            URL url = new URL(properties.getEndpoint() + "/dois/" + handle);

            // RestTemplate restTemplate = new RestTemplate();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            // Prepare the basic auth credentials
            String username = properties.getUser();
            String password = properties.getPassword();
            String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

            httpURLConnection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            DatacitePayloadFactory payloadFactory = new DatacitePayloadFactory();
            String payloadForUpdate = payloadFactory.payloadForUpdate(request, handle);


            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = payloadForUpdate.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                ObjectMapper inputStreamObjectMapper = new ObjectMapper();
                JsonNode inputStreamRootNode = inputStreamObjectMapper.readTree(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Error reading response");
            }

            return handle;
        } catch (Exception e) {
            throw new RuntimeException("Error updating handle");
        }
    }
}