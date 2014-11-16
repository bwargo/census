import grails.test.AbstractCliTestCase

class Get2013SummaryTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGet2013Summary() {

        execute(["get2013-summary"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
