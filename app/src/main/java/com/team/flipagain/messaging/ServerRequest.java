package com.team.flipagain.messaging;

import com.team.flipagain.domain.Bundle;
import com.team.flipagain.domain.FieldOfStudy;
import com.team.flipagain.domain.Module;
import com.team.flipagain.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Philipp & Raffaele on 03.05.2016.
 */
public interface ServerRequest {

    /**
     * Implementiert die Methoden, welche Objekte vom Server zurückgeben.
     * @param user
     * @throws IOException
     * @throws TimeoutException
     */

    public boolean validateUser(User user) throws IOException, TimeoutException;
    public ArrayList<FieldOfStudy> getFieldOfStudyByName();
    public ArrayList<Module> getModuleByName(FieldOfStudy fieldOfStudy) throws IOException, TimeoutException;
    public Bundle downloadBundle(String bundleName) throws IOException, TimeoutException;
    public ArrayList<com.team.flipagain.domain.Bundle> synchronize(User user) throws IOException, TimeoutException;

    }
