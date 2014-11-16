package org.bwargo.census

import grails.rest.*
import org.bson.types.ObjectId

@Resource(formats=['json', 'xml'])
class Variable {

    static mapWith = "mongo"

    ObjectId id

    String name
    String label
    String concept
    String predicateType
    String year

    static mapping = {
        name index:true
    }
    static constraints = {
    }


    //db.variable.find({"concept":/Ancestry/, "label":{$not:/Margin/}}).count()

}
