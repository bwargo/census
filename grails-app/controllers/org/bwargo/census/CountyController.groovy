package org.bwargo.census

import grails.converters.JSON
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.RestController

@RestController
class CountyController {

    SimpleService simpleService
    ResponseParser responseParser

    def index() {
        //returns list of PA counties
        def counties = County.list()
        render(["counties": counties] as JSON)
    }

    def get(){
        if(!params.variable || !params.county || !params.state){
            render(status:400, text:"missing parameters. make sure you include 'variable', 'state' and 'county' params")
            return
        }

        ResponseEntity responseEntity = simpleService.getCountyData(params.county, params.variable)
        ArrayList formattedResults = responseParser.handleResponse(responseEntity)
        render(["status": responseEntity.statusCode, "results":formattedResults] as JSON)

    }

    def all(){
        if (!params.id) {
            render(status: 400, text: "missing parameters. make sure you include 'id' of the state param")
            return
        }

        ResponseEntity responseEntity = simpleService.basicCall("NAME", "county:*", "state:" + params.id)
        ArrayList parsedResponse = responseParser.handleResponse(responseEntity)
        ArrayList formattedResults = simpleService.formatAsJSON((ArrayList) parsedResponse, params.variable)
        render(["status": responseEntity.statusCode, "results": formattedResults] as JSON)
    }

}
