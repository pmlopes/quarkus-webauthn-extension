package xyz.jetdrone.quarkus.webauthn;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.arc.runtime.BeanContainerListener;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class WebauthnRecorder {

    public BeanContainerListener setWebauthnConfig(WebauthnConfig webauthnConfig) {
        return beanContainer -> {
            WebauthnProducer producer = beanContainer.instance(WebauthnProducer.class);
            producer.setWebauthnConfig(webauthnConfig);
        };
    }

    public void setup(BeanContainer container) throws Exception {
        // TODO: add the webauthn handler to the current router?
        WebauthnProducer producer = container.instance(WebauthnProducer.class);
        producer.produceWebauthn();
        // TODO: should the producer add it to the router?
    }
}
