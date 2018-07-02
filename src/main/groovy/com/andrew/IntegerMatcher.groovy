package com.andrew

class IntegerMatcher {
    def min
    def max
    def name

    IntegerMatcher(name, min, max){
        this.min    = min.toInteger()
        this.max    = max.toInteger()
        this.name   = name
    }

    def parseText(text){
        def result  = []

        if (text == '*'){
            result  = min .. max
        } else if (text ==~ /(\d{1,2})/){
            def val1    = ( text =~ /(\d{1,2})/)[0][1]
            result  = [ val1.toInteger() ]
        } else if (text ==~ /(\d{1,2})-(\d{1,2})/){
            def val1    = ( text =~ /(\d{1,2})-(\d{1,2})/)[0][1]
            def val2    = ( text =~ /(\d{1,2})-(\d{1,2})/)[0][2]
            result  = val1.toInteger() .. val2.toInteger()
        } else if (text ==~ /\*\/(\d{1,2})/){
            def val1    = ( text =~ /\*\/(\d{1,2})/)[0][1]

            result  = (min .. max).findAll { 0 == it % val1.toInteger()}
        } else {
//            throw new IntegerMatcherException("Unmatched text ${text}")
            throw new IntegerMatcherException()
        }

        if (result.findAll{ it < min || max < it}){
            throw new IntegerMatcherException()
        }
        return result
    }
}
