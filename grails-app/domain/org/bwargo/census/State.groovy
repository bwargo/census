package org.bwargo.census

import org.bson.types.ObjectId
import grails.rest.*

@Resource(formats=['json', 'xml'])
class State {

    static mapWith = "mongo"


    ObjectId id

    String fipsCode
    String abbreviation
    String name
    String gnisCode

    static mapping = {
        fipsCode index:true
    }

    static constraints = {
    }
}
