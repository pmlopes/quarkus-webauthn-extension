package xyz.jetdrone.quarkus.webauthn;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.arc.deployment.BeanContainerListenerBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

// TODO: I have no idea what this does!
public class WebauthnProcessor {

    private WebauthnConfig webauthnConfig;

    @Record(ExecutionTime.STATIC_INIT)
    @BuildStep
    void build(BuildProducer<AdditionalBeanBuildItem> additionalBeanProducer,
               BuildProducer<FeatureBuildItem> featureProducer,
               WebauthnRecorder recorder,
               BuildProducer<BeanContainerListenerBuildItem> containerListenerProducer) {

        featureProducer.produce(new FeatureBuildItem("webauthn"));

        AdditionalBeanBuildItem unremovableProducer = AdditionalBeanBuildItem.unremovableOf(WebauthnProducer.class);
        additionalBeanProducer.produce(unremovableProducer);

        containerListenerProducer.produce(
                new BeanContainerListenerBuildItem(recorder.setWebauthnConfig(webauthnConfig)));
    }

    @Record(ExecutionTime.RUNTIME_INIT)
    @BuildStep
    void setup(WebauthnRecorder recorder, BeanContainerBuildItem beanContainer) throws Exception {
        recorder.setup(beanContainer.getValue());
    }
}
