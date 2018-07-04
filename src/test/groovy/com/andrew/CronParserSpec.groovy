package com.andrew

import spock.lang.Specification

import com.andrew.CronParser

class CronParserSpec extends Specification {

    def testDefaultConstructor(){
        setup:
            def parser  = new CronParser()

        expect:
            parser.parserConfig.size() == 5
            parser.toString() == '0-59 0-23 1-31 1-12 1-7 Command String'
    }

    def testSimpleParserSuccess(){
        setup:
            def parser  = new CronParser( [ new IntegerMatcher('minute', 0, 59) ])

        when:
            def expression  = parser.parseArgs([ '59',  '/run/some/script' ])

        then:
            parser.parserConfig.size() == 1

            expression.parts[0].name == 'minute'
            expression.parts[0].values == [59]
            expression.parts[1].name == 'command'
            expression.parts[1].values == [ '/run/some/script' ]

        when:
            expression  = parser.parseArgs([ '59',  '/run/some/script', 'with', 'params' ])

        then:
            expression.parts[0].name == 'minute'
            expression.parts[0].values == [59]
            expression.parts[1].name == 'command'
            expression.parts[1].values == ['/run/some/script', 'with', 'params']

        when:
            expression  = parser.parseArgs([ '*/15',  '/run/some/script', 'with', 'params' ])

        then:
            expression.parts[0].name == 'minute'
            expression.parts[0].values == [0, 15, 30, 45]
            expression.parts[1].name == 'command'
            expression.parts[1].values == ['/run/some/script', 'with', 'params']

        when:
            expression  = parser.parseArgs([ '58-59',  '/run/some/script', 'with', 'params' ])

        then:
            expression.parts[0].name == 'minute'
            expression.parts[0].values == [58, 59]
            expression.parts[1].name == 'command'
            expression.parts[1].values == ['/run/some/script', 'with', 'params']
    }


}