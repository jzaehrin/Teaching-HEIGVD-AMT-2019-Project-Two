package ch.heigvd.amt.bidirhandshake.apisspecs.steps.movieapi;

import ch.heigvd.amt.bidirhandshake.apisspecs.steps.World;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.MediaDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.WatchedDTO;
import io.cucumber.java.en.And;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import static org.junit.Assert.*;

public class MediaSteps {
    private World world;

    public MediaSteps(World world) {
        this.world = world;
    }

    @And("I want the page number {int} with page size {int}")
    public void iWantThePageNumber(int pageNumber, int pageSize) {
    }

    @And("^I receive a \\w+ page of size (\\d+)$")
    public void iReceiveAMediaPageOfSize(int size) throws ClassNotFoundException {
        assertEquals(size, this.world.response.getBody().getArray().length());
    }

    @And("I have a well-formed media payload")
    public void iHaveAWellFormedMediaPayload() {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setDuration(120);
        mediaDTO.setMainGenre("Movie");
        mediaDTO.setRating(5);
        mediaDTO.setTitle(RandomStringUtils.randomAlphabetic(10));
        mediaDTO.setRelease(RandomUtils.nextInt(0, 1578048365));

        this.world.body = mediaDTO;
    }

    @And("I have a well-formed towatch payload between userId {int} and mediaId {int}")
    public void iHaveAWellFormedTowatchPayload(int userId, int mediaId) {
        ToWatchDTO toWatchDTO = new ToWatchDTO();
        toWatchDTO.setUserId(userId);
        toWatchDTO.setMediaId(mediaId);

        this.world.body = toWatchDTO;
    }

    @And("I have a well-formed towatch relation between userId {int} and mediaId {int}")
    public void iHaveAWellFormedToWatchRelation(int userId, int mediaId) {
        this.world.loginSteps.iAmLoggedInAs("pete842@mail.com", "totem");
        iHaveAWellFormedTowatchPayload(userId, mediaId);
        this.world.apiSteps.iPOSTItToTheRouteWithToken(String.format("/movie/users/%d/towatch", userId, mediaId));
        this.world.apiSteps.iReceiveAStatusCode(200);
    }

    @And("I have a well-formed watched payload between userId {int} and mediaId {int}")
    public void iHaveAWellFormedWatchedPayloadBetweenUserIdAndMediaId(int userId, int mediaId) {
        WatchedDTO watchedDTO = new WatchedDTO();
        watchedDTO.setMediaId(mediaId);
        watchedDTO.setUserId(userId);
        watchedDTO.setRating(5);
        watchedDTO.setWatched(123456);

        this.world.body = watchedDTO;
    }
}
