package pl.ang.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ang.backend.model.Result;
import pl.ang.backend.service.ResultService;

import java.util.List;
import java.util.Map;

@RestController
public class ResultController {

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/api/get-role-results", method = RequestMethod.GET)
    public List<Result> getResultsForRole(@RequestParam Integer roleId, @RequestParam(required = false) Integer readingVideoTestId, @RequestParam(required = false) Integer blankInsertTestId) {
        return resultService.getResultsForRole(roleId, readingVideoTestId, blankInsertTestId);
    }

    @RequestMapping(value = "/api/get-user-results/{id}", method = RequestMethod.GET)
    public List<Result> getResultForUser(@PathVariable long id) {
        return resultService.getResultForUser(id);
    }
}
