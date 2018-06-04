package org.leagueoftests.app.jbhsel;

import org.jbehave.web.selenium.WebDriverProvider;

import org.leagueoftests.app.jbhsel.pages.*;


/**
 * Created by tp on 03.04.17.
 */
public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private Github github;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public Github github() {
        if (github == null) {
            github = new Github(driverProvider);
        }
        return github;
    }
}
