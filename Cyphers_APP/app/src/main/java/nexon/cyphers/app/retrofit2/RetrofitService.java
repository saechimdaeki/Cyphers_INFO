package nexon.cyphers.app.retrofit2;

import nexon.cyphers.app.model.Character.CharacterInformation;
import nexon.cyphers.app.model.PlayerInfo;
import nexon.cyphers.app.model.PlayerModel;
import nexon.cyphers.app.model.TotalRankRow;
import nexon.cyphers.app.model.matching_Detail.MatchingDetailModel;
import nexon.cyphers.app.model.matching_record.matchingRecordModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("cy/players/")
    Call<PlayerModel> GetPlayerBasic(@Query("nickname") String nickname,
                              @Query("apikey") String apikey);

    @GET("cy/players/{playerId}")
    Call<PlayerInfo> GetPlayerDetail(@Path("playerId") String playerId,
                                     @Query("apikey") String apikey);

    @GET("cy/ranking/ratingpoint")
    Call<TotalRankRow> GetTotalRANKING(@Query("playerId")String playerId,
                                       @Query("apikey") String apikey);

    @GET("cy/players/{playerId}/matches")
    Call<matchingRecordModel> GetPlayerMatchingRecord(
            @Path("playerId") String playerId,
            @Query("gameTypeId") String gameTypeId,
            @Query("limit") Integer limit,
            @Query("apikey")String apikey);

    @GET("cy/characters")
    Call<CharacterInformation> GetCharacterUniqueID(
            @Query("apikey") String apikey
    );
    @GET("cy/matches/{matchId}")
    Call<MatchingDetailModel> GetMatchingDetail(
            @Path("matchId") String matchId,
            @Query("apikey") String apikey
    );
}
