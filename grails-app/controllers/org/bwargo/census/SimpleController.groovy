package org.bwargo.census

import grails.converters.JSON
import org.springframework.http.ResponseEntity

class SimpleController {

    SimpleService simpleService
    ResponseParser responseParser

    def index() {
    }

    def get() {
        if (!params.state || !params.variable) {
            render(status: 400, text: "missing parameters. make sure you include 'state' and 'variable' params")
            return
        }
        String forParam = "county:*"; //defaulting to all counties for now if no params sent
        if (params.county) {
            forParam = "county:" + params.county
        } else if (params.place) {
            forParam = "place:" + params.place
        }
        ResponseEntity responseEntity = simpleService.basicCall("NAME," + params.variable, forParam, "state:" + params.state)
        ArrayList parsedResponse = responseParser.handleResponse(responseEntity)
        ArrayList formattedResults = simpleService.formatAsJSON((ArrayList) parsedResponse, params.variable)
        render(["status": responseEntity.statusCode, "results": formattedResults] as JSON)
    }

    def all() {

    }

}
