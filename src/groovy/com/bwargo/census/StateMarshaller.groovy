package com.bwargo.census

import grails.converters.JSON
import org.bwargo.census.State

/**
 * Created by bwargo on 10/29/14.
 */
class StateMarshaller {
    void register() {
        JSON.registerObjectMarshaller(State) { State state ->
            return [
                    id : state.id.toString(),
                    fips : state.fipsCode,
                    name : state.name,
                    abbrv : state.abbreviation,
                    gnis : state.gnisCode
            ]
        }
    }
}
