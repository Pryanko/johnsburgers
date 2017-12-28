package com.examle.libgo.johnsburgers.data.repository;

import com.examle.libgo.johnsburgers.data.parcelers.ServerResponse;

/**
 * @author libgo (22.12.2017)
 */

public class AppRepository {

    private Boolean downloading = false;
    private ServerResponse serverResponse;

    public void okResponse(ServerResponse serverResponse){
        this.serverResponse = serverResponse;
        downloading = true;
    }

    public ServerResponse getServerResponse(){
        return serverResponse;
    }

    public  Boolean getDownloading(){
        return downloading;
    }

}
