package org.example

import grails.test.mixin.TestFor
import org.bwargo.census.County
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(County)
class CountySpec extends Specification {

    def setup() {
	println "first setup"
    }

    def cleanup() {
	println "first cleanup"
    }

    void "test something"() {
	given: "test given"
		println "first given"
	when: "test when"
		println "first when"
	then: "test then"
		println "first then"
    }
}
