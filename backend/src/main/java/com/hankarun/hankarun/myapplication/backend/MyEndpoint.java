/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.hankarun.hankarun.myapplication.backend;

import com.hankarun.JokeTeller;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.hankarun.example.com",
                ownerName = "backend.myapplication.hankarun.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "tellAJoke")
    public MyBean tellAJoke() {
        JokeTeller jokeTeller = new JokeTeller();
        MyBean response = new MyBean();
        response.setData(jokeTeller.getmJoke());

        return response;
    }

}
