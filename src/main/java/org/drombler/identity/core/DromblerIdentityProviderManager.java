package org.drombler.identity.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Florian
 */
public class DromblerIdentityProviderManager {

    private final Map<String, DromblerIdentityProvider> dromblerIdentityProviders = new HashMap<>();

    public DromblerIdentityProvider registerDromblerIdentityProvider(DromblerIdentityProvider dromblerIdentityProvider) {
        return dromblerIdentityProviders.put(dromblerIdentityProvider.getDromblerIdentityProviderId(), dromblerIdentityProvider);
    }

    public DromblerIdentityProvider getDromblerIdentityProvider(String dromblerIdentityProviderId) {
        return dromblerIdentityProviders.get(dromblerIdentityProviderId);
    }

}
