package org.bwargo.census

import grails.converters.JSON

class VariableController {


    def index() {
        render(["variables": Variable.find()] as JSON)
    }

    def get() {

    }

    def ancestry() {
        //find all First Ancestry B04001_[digits]E
        //find all Total Ancestry Reported B04003_[digits]E
        def query = Variable.where {
            (name ==~ ~/B04003_.*E/) && (year == "2013")
        }

        render(["variables": query.findAll()] as JSON)

        //render(["variables": Variable.findAllByConceptLike(["concept": {$regex : /Ancestry/}, "label": { $not: {$regex: /Margin/} }])] as JSON)
    }
}
