/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.identity.core;

import java.util.Objects;

/**
 *
 * @author Florian
 */
public class DromblerUserId implements DromblerId {

    private final String dromblerIdString;
    private DromblerIdentityProvider dromblerIdentityProvider;

    public DromblerUserId(String dromblerIdString) {
        this(dromblerIdString, PrivateDromblerIdProvider.getInstance());
    }

    public DromblerUserId(String dromblerIdString, DromblerIdentityProvider dromblerIdentityProvider) {
        this.dromblerIdString = dromblerIdString;
        this.dromblerIdentityProvider = dromblerIdentityProvider;
    }

    @Override
    public String getDromblerIdString() {
        return dromblerIdString;
    }

    @Override
    public String getDromblerIdFormatted() {
        return getDromblerIdentityProvider().isPrivate() ? getDromblerIdString()
                : getDromblerIdString() + "@" + getDromblerIdentityProvider().getDromblerIdentityProviderId();
    }

    public static final DromblerUserId parseDromblerUserId(String dromblerIdFormatted, DromblerIdentityProviderManager dromblerIdentityProviderManager) {
        String[] dromblerIdParts = dromblerIdFormatted.split("@");
        if (dromblerIdParts.length == 1) {
            return new DromblerUserId(dromblerIdParts[0]);
        } else {
            if (dromblerIdParts.length != 2) {
                throw new IllegalArgumentException("Unexpected format: " + dromblerIdFormatted);
            }
            final DromblerIdentityProvider dromblerIdentityProvider = dromblerIdentityProviderManager.getDromblerIdentityProvider(dromblerIdParts[1]);
            if (dromblerIdentityProvider == null) {
                throw new IllegalArgumentException("Unknown dromblerIdentityProvider id: " + dromblerIdParts[1]);
            }
            return new DromblerUserId(dromblerIdParts[0], dromblerIdentityProvider);
        }
    }

    @Override
    public DromblerIdentityProvider getDromblerIdentityProvider() {
        return dromblerIdentityProvider;
    }

    @Override
    public DromblerIdType getType() {
        return DromblerIdType.USER;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.dromblerIdString);
        hash = 89 * hash + Objects.hashCode(this.dromblerIdentityProvider);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DromblerId)) {
            return false;
        }

        final DromblerUserId other = (DromblerUserId) obj;
        return Objects.equals(this.dromblerIdString, other.dromblerIdString)
                && Objects.equals(this.dromblerIdentityProvider, other.dromblerIdentityProvider);
    }

    @Override
    public String toString() {
        return "DromblerUserId{" + "dromblerIdString=" + dromblerIdString + ", dromblerIdentityProvider=" + dromblerIdentityProvider + '}';
    }

}
