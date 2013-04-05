package example;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 8:25 PM
 */
public class EchoServer {
    public String echo(String input) {
        if (MyFeatureToggle.TEST_FEATURE.isEnabled()) {
            return "Toggle is on, " + input;
        }
        return "Toggle is off, " + input;
    }

    public String sayHello(String input) {
        return "Hello, " + input;
    }

    public String multipleToggleEcho(String input) {
        StringBuilder sb = new StringBuilder();

        if (MyFeatureToggle.TEST_FEATURE.isEnabled()) {
            sb.append("TestFeature is on, ");
        }

        if (MyFeatureToggle.STAGE1.isEnabled()) {
            sb.append("Stage 1 is on, ");
        }
        if (MyFeatureToggle.STAGE2.isEnabled()) {
            sb.append("Stage 2 is on, ");
        }
        if (MyFeatureToggle2.TEST_FEATURE_2.isEnabled()) {
            sb.append("TestFeature 2 is on, ");
        }
        sb.append(input);
        return sb.toString();

    }
}
