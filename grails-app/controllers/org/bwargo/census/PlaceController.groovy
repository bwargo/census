package org.bwargo.census

import grails.converters.JSON
import org.springframework.http.ResponseEntity

class PlaceController {

    SimpleService simpleService
    ResponseParser responseParser

    def index() {}

    def get() {

    }

    def all() {
        //http://api.org.bwargo.census.gov/data/2013/acs3?get=NAME&for=place:*&in=state:42&key=d630ed4bf2adc05b83316e0f10ea1d6113bc9070
        if (!params.id) {
            render(status: 400, text: "missing parameters. make sure you include 'id' param")
            return
        }

        ResponseEntity responseEntity = simpleService.basicCall("NAME", "place:*", "state:" + params.id)
        ArrayList parsedResponse = responseParser.handleResponse(responseEntity)
        ArrayList formattedResults = simpleService.formatAsJSON((ArrayList) parsedResponse, params.variable)
        render(["status": responseEntity.statusCode, "results": formattedResults] as JSON)
    }
}
