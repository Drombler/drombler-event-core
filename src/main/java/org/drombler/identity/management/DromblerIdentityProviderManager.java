package org.drombler.identity.management;

import java.util.HashMap;
import java.util.Map;
import org.drombler.identity.core.DromblerIdentityProvider;

/**
 *
 * @author Florian
 */
public class DromblerIdentityProviderManager {

    private final Map<String, DromblerIdentityProvider> dromblerIdentityProviders = new HashMap<>();

    public DromblerIdentityProvider registerDromblerIdentityProvider(DromblerIdentityProvider dromblerIdentityProvider) {
        synchronized (dromblerIdentityProviders) {
            return dromblerIdentityProviders.put(dromblerIdentityProvider.getDromblerIdentityProviderId(), dromblerIdentityProvider);
        }
    }

    public DromblerIdentityProvider getDromblerIdentityProvider(String dromblerIdentityProviderId) {
        synchronized (dromblerIdentityProviders) {
            return dromblerIdentityProviders.get(dromblerIdentityProviderId);
        }
    }

}
