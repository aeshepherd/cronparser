package com.andrew

import spock.lang.Specification

import com.andrew.CronParser

class CronExpressionSpec extends Specification {

    // progressively add parts and check that output is correct
    def testSimple() {
        setup:
        def expression = new CronExpression()
        def expected = ""

        when:
        expression.addPart('minute', [1])
        expected = "minute          1\r\n"
        then:
        expression.toString() == expected

        when:
        expression.addPart('hour', [2])
        expected += "hour            2\r\n"
        then:
        expression.toString() == expected

        when:
        expression.addPart('day of month', [3])
        expected += "day of month    3\r\n"
        then:
        expression.toString() == expected

    }

    def testMultiple() {
        setup:
        def expression = new CronExpression()
        def expected = ""

        when:
        expression.addPart(name, values)
        expected += outputText

        then:
        expression.toString() == expected

        where:
        name           | values | outputText
        'minute'       | [1]    | "minute          1\r\n"
        'hour'         | [2]    | "hour            2\r\n"
        'day of month' | [3]    | "day of month    3\r\n"
        'month'        | [4]    | "month           4\r\n"
        'day of week'  | [5]    | "day of week     5\r\n"

    }

    def testMultipleComplex() {
        setup:
        def expression = new CronExpression()
        def expected = ""

        when:
        expression.addPart(name, values)
        expected += outputText

        then:
        expression.toString() == expected

        where:
        name     | values    | outputText
        'minute' | [1, 3, 5] | "minute          1 3 5\r\n"
        'hour' | [1, 2, 6]   | "hour            1 2 6\r\n"
        'day of month' | [1, 31] | "day of month    1 31\r\n"
        'month' | (1..12) |    "month           1 2 3 4 5 6 7 8 9 10 11 12\r\n"
        'day of week' | (1..7) |   "day of week     1 2 3 4 5 6 7\r\n"
        'command' | [ '/usr/bin/find' ] | "command         /usr/bin/find\r\n"

    }
}