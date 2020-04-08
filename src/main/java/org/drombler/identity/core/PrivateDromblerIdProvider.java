/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.identity.core;

/**
 *
 * @author Florian
 */
public class PrivateDromblerIdProvider implements DromblerIdentityProvider {

    private static final PrivateDromblerIdProvider INSTANCE = new PrivateDromblerIdProvider();

    private PrivateDromblerIdProvider() {
    }

    public static final PrivateDromblerIdProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public String getDromblerIdentityProviderId() {
        return "private"; // TODO: ""?
    }

    @Override
    public boolean isPrivate() {
        return true;
    }

    @Override
    public String toString() {
        return "PrivateDromblerIdProvider{" + '}';
    }

}
