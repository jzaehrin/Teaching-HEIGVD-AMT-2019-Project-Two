package ch.heigvd.amt.bidirhandshake.movieapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.movieapi.MediaApi;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.MediaDTOHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.MediaDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.Media;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class MediaApiController implements MediaApi {

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public ResponseEntity<MediaDTO> addMedia(String authorization, @Valid MediaDTO mediaDTO) throws Exception {
        Media media = mediaRepository.save(MediaDTOHelper.toEntity(mediaDTO));

        return ResponseEntity.ok().body(MediaDTOHelper.fromEntity(media));
    }

    @Override
    public ResponseEntity<List<MediaDTO>> getMedias(String authorization, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        Page<Media> medias = mediaRepository.findAll(PageRequest.of(pageNumber, pageSize));

        return ResponseEntity.ok().body(MediaDTOHelper.fromEntity(medias.toList()));
    }
}
