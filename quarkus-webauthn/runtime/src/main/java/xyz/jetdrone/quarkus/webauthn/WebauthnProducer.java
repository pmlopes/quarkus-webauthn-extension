package xyz.jetdrone.quarkus.webauthn;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.webauthn.WebAuthn;
import io.vertx.ext.auth.webauthn.WebAuthnOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.WebAuthnHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class WebauthnProducer {

    // TODO: this class should create the WebAuthn, for this we need 2 things:
    @Inject
    Vertx vertx;
    @Inject
    Router router;

    private WebauthnConfig webauthnConfig;

    @Produces
    public WebAuthnHandler produceWebauthn() throws Exception {
        return WebAuthnHandler.create(
                // TODO: given the config, create the vert.x config object
                WebAuthn.create(vertx, new WebAuthnOptions())
                        // TODO: we need user supplied functions here?
                        //       how do we inject that?
                        .authenticatorFetcher(authr -> { return null; })
                        .authenticatorUpdater(authr -> { return null; }))
                .setOrigin(webauthnConfig.origin)
                // TODO: here we need 3 routes to mount the callbacks: /webauthn/callback, /webauthn/get, /webauthn/create ?
                .setupCallback(router.route("/webauthn"))
                .setupCredentialsGetCallback(router.route("/webauthn/get"))
                .setupCredentialsCreateCallback(router.route("/webauthn/create"));
    }

    public void setWebauthnConfig(WebauthnConfig webauthnConfig) {
        this.webauthnConfig = webauthnConfig;
    }
}