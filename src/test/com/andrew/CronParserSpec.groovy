package com.andrew

import spock.lang.Specification


class CronParserSpec extends Specification {

    def testConstructor(){
        setup:
            def parser  = new CronParser()

        expect:
            parser.parserConfig.size() == 5
    }
}