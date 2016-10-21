package usjt.graincare.rest;

import retrofit2.Call;
import retrofit2.Callback;
import usjt.graincare.json.GrainCareApi;

/**
 * Created by shozo on 21/10/16.
 */
public class SiloHistoryPostRest {
    private GrainCareApi api;
    //SiloHistoryDTO siloHist = new SiloHistoryDTO();
    //Call<SiloHistoryDTO> call = api.closeSilo(siloHist);
    /*call.enqueue(new Callback<SiloHistoryDTO>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                requestBuyerInfo(callback);
            } else {
                callback.invalidInfo();
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.d(TAG, "Error on processing login", t);
            callback.error();
        }
    });*/

}
