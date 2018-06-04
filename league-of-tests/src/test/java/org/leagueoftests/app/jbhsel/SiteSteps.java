package org.leagueoftests.app.jbhsel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.leagueoftests.app.jbhsel.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on Github profile page")
    public void userOnProfilePage() {

        pages.github().open();
    }

    @When("user clicks the $linkText link")
    public void userClicksTabLink(String linkText) {

        pages.github().click(linkText);
    }
    
    @Then("the element $articleName is displayed")
    public void thenTheArticleInstrukcjeIsDisplayed(String articleName) {
        assertTrue(pages.github().isPresent(articleName));
    }

    @Then("the element $articleName is not displayed")
    public void thenTheArticleInstrukcjeIsNotDisplayed(String articleName) {
        assertFalse(pages.github().isPresent(articleName));
    }

}
