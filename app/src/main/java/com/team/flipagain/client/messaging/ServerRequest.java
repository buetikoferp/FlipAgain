package com.team.flipagain.client.messaging;

import com.team.flipagain.client.domain.Bundle;
import com.team.flipagain.client.domain.FieldOfStudy;
import com.team.flipagain.client.domain.Module;
import com.team.flipagain.client.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp & Raffaele on 03.05.2016.
 */
public interface ServerRequest {

    public User addUser(User user) throws IOException, TimeoutException;
    public User validateUser(User user) throws IOException, TimeoutException;
    public ArrayList<FieldOfStudy> getFieldOfStudyByName();
    public ArrayList<Module> getModuleByName();
    public Bundle downloadBundle(String bundleName) throws IOException, TimeoutException;

    }
