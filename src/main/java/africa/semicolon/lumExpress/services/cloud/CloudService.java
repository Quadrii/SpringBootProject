package africa.semicolon.lumExpress.services.cloud;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
public interface CloudService {
    String upload(byte[] imageBytes, Map<?,?> map) throws IOException;
}
