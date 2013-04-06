#Toggle
======
Feature Toggle is common concept in the current software development process, especially if you want to do continous dilivery. But there is a pain for developer to test both Feature Toggle On and Off logic. Because most of Feature Toggle is just a Enum, It's static, It's hard to change the status of one Feature Toggle dynamicly. That's why i write this project.
**Toggle is a little java tool, which can help you easily test both  Feature Toggle on and off logic in one test class.** 

##How-TO
I'm trying to push this project into Maven central repository, Now it's waiting aprroval from Sonatype. when it's done, we can easily using maven depency include this little tool.

###step1: Include This Tool Into Your Project
There are two method to include this tool into your project.
1. Download the jar and add it into your project classpath
2. Using Maven depency

###step1：Define Your Own Toggle Class
Your feature toggle class must implements interface Toggle and must using ToggleStatus enum as the switch of Feature Toggle.
```java
public enum MyFeatureToggle implements Toggle {
	    TEST_FEATURE(ToggleStatus.ON), STAGE1(ToggleStatus.ON), STAGE2(ToggleStatus.OFF);
	        private ToggleStatus status;

		    MyFeatureToggle(ToggleStatus status) {
			            this.status = status;
				        }

		        @Override
				    public void setStatus(ToggleStatus status) {
					            this.status = status;
						        }

			    public boolean isEnabled() {
				            return status.equals(ToggleStatus.ON);
					        }

			        public ToggleStatus getStatus() {
					        return status;
						    }

				    public static Map<MyFeatureToggle, ToggleStatus> getStatusMap() {
					            Map<MyFeatureToggle, ToggleStatus> statusMap = new HashMap<MyFeatureToggle, ToggleStatus>();
						            for (MyFeatureToggle toggle : MyFeatureToggle.values()) {
								                statusMap.put(toggle, toggle.getStatus());
										        }
							            return statusMap;
								        }
}
```

###step2: Apply Feature Toggle Test Rule To Your Test Class
Make sure the ToggleRule property is public.
```java
    @Rule
        public ToggleRule anotherToggleRule = new ToggleRule();
```

###step3: Add Annotation On the Test Method To Decide The Toggle Status When Run This Test
Now, this annotation support single, multiple and mixed Toggles.
```java
    @Test
        @ToggleEnabled(toggleClass = "example.MyFeatureToggle",toggleNames = "TEST_FEATURE")
	    public void testEchoToggleOn() throws Exception {
		            assertThat(server.echo("xianlinbox"), is("Toggle is on, xianlinbox"));
			        }


    @Test
        @ToggleEnabled(toggleClass = "example.MyFeatureToggle",toggleNames = {"STAGE1","STAGE2"})
	    public void testMultipleToggle() throws Exception {
		            assertThat(server.multipleToggleEcho("xianlinbox"), is("TestFeature is on, Stage 1 is on, Stage 2 is on, xianlinbox"));

			        }

    @Test
        @ToggleDisabled(toggleClass = "example.MyFeatureToggle",toggleNames = {"STAGE1","TEST_FEATURE"})
	    @ToggleEnabled(toggleClass = "example.MyFeatureToggle",toggleNames = "STAGE2")
	        public void testMixedToggle() throws Exception {
			        assertThat(server.multipleToggleEcho("xianlinbox"), is("Stage 2 is on, xianlinbox"));

				    }

    @Test
        @ToggleDisabled(toggleClass = "example.MyFeatureToggle", toggleNames = {"STAGE1", "TEST_FEATURE"})
	    @ToggleEnabled(toggleClass = "example.MyFeatureToggle2", toggleNames = "TEST_FEATURE_2")
	        public void testMixedMultipleToggle() throws Exception {
			        assertThat(server.multipleToggleEcho("xianlinbox"), is("TestFeature 2 is on, xianlinbox"));

				    }
```

That's all , Hope you enjoy it.
