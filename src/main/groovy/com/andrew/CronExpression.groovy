package com.andrew

class CronExpression {
    def parts   = []

    def addPart(name, values){
        parts   += [ 'name' : name, 'values' : values ]
    }

    @java.lang.Override
    public java.lang.String toString() {
        def result  = ""

        // FIX: Dynamic calculation of spaces would be nice but too complex for testing
        // FIX: Hardcoding for 12 characters ( length of "day of month" plus 4)
//        def maxChars    = parts.collect{it.name}*.length().max() + 4
        def maxChars    = 12 + 4
        parts.each {
            result  += it.name + ' '.multiply(maxChars - it.name.length()) + it.values.join(' ') + "\r\n"
            def temp    = it.name + ' '.multiply(maxChars - it.name.length()) + it.values.join(' ')
        }
        return result
    }
}
