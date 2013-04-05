package example;

import com.xianlinbox.toggle.AnotherToggleRule;
import com.xianlinbox.toggle.ToggleDisabled;
import com.xianlinbox.toggle.ToggleEnabled;
import com.xianlinbox.toggle.ToggleRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 8:27 PM
 */
public class EchoServerTest {
    EchoServer server;

    @Rule
    public AnotherToggleRule anotherToggleRule = new AnotherToggleRule();

    @Before
    public void setUp() throws Exception {
        server = new EchoServer();

    }

    @Test
    public void testNoToggleAnnotation() throws Exception {
        assertThat(server.sayHello("xianlinbox"), is("Hello, xianlinbox"));
    }

    @Test
    @ToggleDisabled(toggleClass = "example.MyFeatureToggle",toggleNames = "TEST_FEATURE")
    public void testEchoToggleOff() throws Exception {
        assertThat(server.echo("xianlinbox"), is("Toggle is off, xianlinbox"));
    }

    @Test
    @ToggleEnabled(toggleClass = "example.MyFeatureToggle",toggleNames = "TEST_FEATURE")
    public void testEchoToggleOn() throws Exception {
        assertThat(server.echo("xianlinbox"), is("Toggle is on, xianlinbox"));
    }

//
//    @Test
//    @ToggleEnabled({MyFeatureToggle.STAGE1, MyFeatureToggle.STAGE2})
//    public void testMultipleToggle() throws Exception {
//        assertThat(server.multipleToggleEcho("xianlinbox"), is("TestFeature is on, Stage 1 is on, Stage 2 is on, xianlinbox"));
//
//    }
//
//    @Test
//    @ToggleDisabled({MyFeatureToggle.TEST_FEATURE,MyFeatureToggle.STAGE1})
//    @ToggleEnabled(MyFeatureToggle.STAGE2)
//    public void testMixedToggle() throws Exception {
//        assertThat(server.multipleToggleEcho("xianlinbox"), is("Stage 2 is on, xianlinbox"));
//
//    }
}
