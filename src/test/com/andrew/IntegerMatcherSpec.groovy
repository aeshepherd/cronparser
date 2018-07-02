package com.andrew

import spock.lang.Specification


class IntegerMatcherSpec extends Specification {


    def testConstructor() {
        setup:
        def matcher = new IntegerMatcher('test', 0, 5)

        expect:
        matcher.min == 0
        matcher.max == 5
        matcher.name == 'test'
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
            thrown(IntegerMatcherException)

        where:
        text | exceptionMessage
        '10' || 'Value out of range: 10'
        '100' || 'Value out of range: 100'
        '6' || 'Value out of range: 9'
        'a' || 'Unexpected text'
        '-3' || 'Unexpected text'
        '0' || 'Value out of range: 0'
    }
}