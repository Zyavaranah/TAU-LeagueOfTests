package org.leagueoftests.web;

import org.leagueoftests.app.Champions;
import org.leagueoftests.repository.ChampionsRepository;
import org.leagueoftests.repository.ChampionsRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ChampionsApi {

    @Autowired
    ChampionsRepository championsRepository;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(value = "/leagueoftests/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Champions getChampion(@PathVariable("id") int id) throws SQLException {
        championsRepository = ChampionsRepositoryFactory.getInstance();
        return championsRepository.getById(id);
    }

    @RequestMapping(value = "/leagueoftests", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Champions> getChampions() throws SQLException {
        championsRepository = ChampionsRepositoryFactory.getInstance();
        List<Champions> champions = new LinkedList<Champions>();
        for (Champions c : championsRepository.getAll()) {
                champions.add(c);
        }
        return champions;
    }

    @RequestMapping(value = "/leagueoftests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long addChampion(@RequestBody Champions c) {
        championsRepository = ChampionsRepositoryFactory.getInstance();
        return new Long(championsRepository.add(c));
    }

    @RequestMapping(value = "/leagueoftests/{id}", method = RequestMethod.DELETE)
    public Long deleteChampion(@PathVariable("id") int id) throws SQLException {
        championsRepository = ChampionsRepositoryFactory.getInstance();
        return new Long(championsRepository.delete(championsRepository.getById(id)));
    }

    @RequestMapping(value = "/leagueoftests/{id}", method = RequestMethod.PUT)
    public Long updateChampion(@PathVariable("id") int id, @RequestBody Champions c) throws SQLException {
        championsRepository = ChampionsRepositoryFactory.getInstance();
        Champions oldChampion = new Champions();
        oldChampion.setId(id);
        return new Long(championsRepository.update(oldChampion, c));
    }

}