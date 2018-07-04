package com.andrew

import spock.lang.Specification


class IntegerMatcherSpec extends Specification {


    def testConstructorSuccess() {
        setup:
        def matcher = new IntegerMatcher('test', 0, 5)

        expect:
        matcher.min == 0
        matcher.max == 5
        matcher.name == 'test'
    }

    def testConstructorFail() {

        when:
            def matcher = new IntegerMatcher(name, min, max)

        then:
            def e   = thrown(exceptionClass)
            e.message   == exceptionMessage

        where:
        name|min|max|exceptionClass|exceptionMessage
        'test' | 5 | 0 | IntegerMatcherException.class| 'Max (0) is less than min (5)'
        'test' | 0 | 'andrew' | NumberFormatException.class | 'For input string: "andrew"'
//        'test' | 'fred' | 5 | NumberFormatException.class | 'Invalid number'
//        'test' | -1 | 5 | IntegerMatcherException.class | 'Out of range'
    }


    def testMatchCreation(){
        setup:
            def matcher = new IntegerMatcher('test', 1, 5)

        expect:
            matcher.parseText(text) == returnVals
        where:
        text | returnVals
        '*' || [1, 2, 3, 4, 5]
        '1' || [1]
        '*/1' || [1, 2, 3, 4, 5]
        '*/2' || [2, 4]
        '1-3' || [1, 2, 3]
        '4-4' || [4]
        '4-5' || [4, 5]
    }


    def testMatchFail(){
        setup:
            def matcher = new IntegerMatcher('test', 1, 5)

        when:
            matcher.parseText(text)

        then:
            IntegerMatcherException e   = thrown()
            e.message   == exceptionMessage

        where:
        text | exceptionMessage
        '10' || 'Value out of range: 10. Range [1, 5]'
        '100' || 'Number too big: 100'
        '6' || 'Value out of range: 6. Range [1, 5]'
        'a' || 'Unmatched text "a"'
        '-3' || 'Unmatched text "-3"'
        '0' || 'Value out of range: 0. Range [1, 5]'
    }

    def testMatchFailSpecifc(){
        setup:
        def matcher = new IntegerMatcher('test', 1, 5)

        when:
            matcher.parseText('10')

        then:
            IntegerMatcherException e   = thrown()
            e.message   == 'Value out of range: 10. Range [1, 5]'
    }

}