package org.leagueoftests.app.jbhsel;
import org.leagueoftests.app.*;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.DelegatingStepMonitor;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.StepType;
import org.jbehave.core.model.StepPattern;
import org.junit.Test;
import org.jbehave.web.selenium.*;
import com.thoughtworks.selenium.*;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import org.jbehave.core.steps.*;
import org.openqa.selenium.remote.DesiredCapabilities;



import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class SiteStories extends JUnitStories {

    private WebDriverProvider driverProvider =
            new TypeWebDriverProvider(PhantomJSDriver.class);
    private WebDriverSteps lifecycleSteps =
            new PerStoryWebDriverSteps(driverProvider); // or PerStoryWebDriverSteps(driverProvider)
    private Pages pages = new Pages(driverProvider);
    private SeleniumContext context = new SeleniumContext();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);

    public SiteStories() {

        System.setProperty("takesScreenshot", "true");
        System.setProperty(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "C:/Users/Marta Siejka/Downloads/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe"
        );
//         System.setProperty("webdriver.chrome.driver", "/..../chromedriver/chromedriver");
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration()
                .useSeleniumContext(context)
                .useWebDriverProvider(driverProvider)
                .useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(Format.ANSI_CONSOLE, Format.HTML, Format.HTML_TEMPLATE));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory(configuration,
                new SiteSteps(pages),
                lifecycleSteps,
                new WebDriverScreenshotOnFailure(
                        driverProvider, configuration.storyReporterBuilder()));
    }
    @Override
    protected List<String> storyPaths() {
         List<String> s = new StoryFinder().findPaths(
                codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story"), null);
                return s;
    }

}
