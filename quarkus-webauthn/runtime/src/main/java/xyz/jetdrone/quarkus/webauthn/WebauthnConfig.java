package xyz.jetdrone.quarkus.webauthn;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

/**
 * This will allow us to create the WebAuthnOptions object + the Origin config for the WebAuthnHandler
 */
@ConfigRoot(name = "webauthn", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public final class WebauthnConfig {
    /**
     * FIDO2 Origin configuration for the server
     */
    @ConfigItem
    public String origin;

    // TODO: we need to reconstruct WebAuthnOptions too
}