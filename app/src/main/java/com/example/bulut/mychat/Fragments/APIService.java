package com.example.bulut.mychat.Fragments;



import com.example.bulut.mychat.Notifications.MyResponse;
import com.example.bulut.mychat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAnZIUEs4:APA91bHUzm-rfCps_0T2n4OMPjOboDCOmrn1_R-LAajBcstmXbNXV0TnpZEqIZV0QS2TzhzBa8LTVObMfc5-bbEhQ28yZsikNtzF5eASdmTqJOG8bbBpDhsI4jldMkp9y4lluIj3qQZR"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
