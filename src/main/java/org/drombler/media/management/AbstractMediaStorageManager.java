package org.drombler.media.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.drombler.media.core.MediaSource;
import org.drombler.media.core.MediaStorage;
import org.drombler.media.management.config.model.json.MediaStorageConfig;
import org.drombler.media.management.config.model.json.MediaStorageConfiguration;

/**
 *
 * @author Florian
 * @param <M>
 * @param <S>
 */
public abstract class AbstractMediaStorageManager<M extends MediaSource<M>, S extends MediaStorage<M>> {

    private final List<S> mediaStorages = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AbstractMediaStorageManager() {
    }

    public void loadJsonConfig(InputStream is) throws IOException {
        synchronized (mediaStorages) {
            MediaStorageConfig mediaStorageConfig = objectMapper.readValue(is, MediaStorageConfig.class);
            mediaStorageConfig.getMediaStorageConfigurations().stream()
                    .map(this::createMediaStorage)
                    .forEach(this::addMediaStorage);
        }
    }

    public void storeJsonConfig(OutputStream os) throws IOException {
        synchronized (mediaStorages) {
            List<MediaStorageConfiguration> mediaStorageConfigurations = mediaStorages.stream()
                    .map(this::extractMediaStorageConfiguration)
                    .collect(Collectors.toList());
            
            MediaStorageConfig mediaStorageConfig = new MediaStorageConfig();
            mediaStorageConfig.setMediaStorageConfigurations(mediaStorageConfigurations);
            objectMapper.writeValue(os, mediaStorageConfig);
        }

    }

    protected abstract S createMediaStorage(MediaStorageConfiguration configuration);

    private MediaStorageConfiguration extractMediaStorageConfiguration(S mediaStorage) {
        MediaStorageConfiguration mediaStorageConfiguration = new MediaStorageConfiguration();
        mediaStorageConfiguration.setName(mediaStorage.getName());
        mediaStorageConfiguration.setMediaRootDir(mediaStorage.getMediaRootDir().toString());
        // TODO: setPrivate
//        mediaStorageConfiguration.setPrivate();
        return mediaStorageConfiguration;
    }

    public boolean addMediaStorage(S mediaStorage) {
        synchronized (mediaStorages) {
            return mediaStorages.add(mediaStorage);
        }
    }

    public boolean removeMediaStorage(S mediaStorage) {
        synchronized (mediaStorages) {
            return mediaStorages.remove(mediaStorage);
        }
    }
}
