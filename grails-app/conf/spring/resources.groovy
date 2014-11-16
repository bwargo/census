import org.bwargo.census.SimpleService
import com.bwargo.census.CustomObjectMarshallers
import com.bwargo.census.StateMarshaller
import com.bwargo.census.VariableMarshaller
import grails.plugins.rest.client.RestBuilder
import org.bwargo.census.ResponseParser

// Place your Spring DSL code here
beans = {

    rest(RestBuilder)

    simpleService(SimpleService){
        host = grailsApplication.config.censusURL
    }

    responseParser(ResponseParser){}

    customObjectMarshallers( CustomObjectMarshallers ) {
        marshallers = [
                new StateMarshaller(),
                new VariableMarshaller()
        ]
    }
}
