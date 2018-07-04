package com.andrew

class IntegerMatcher {
    def min
    def max
    def name

    IntegerMatcher(name, min, max){

        this.min    = min.toInteger()
        this.max    = max.toInteger()
        this.name   = name

        if ( this.max < this.min){
            throw new IntegerMatcherException("Max (${max}) is less than min (${min})")
        } else if (this.max < 0 || 99 < this.max) {
            throw new IntegerMatcherException("Max (${max} out of range [0, 99]")
        } else if (this.min < 0 || 99 < this.min) {
            throw new IntegerMatcherException("Min ($min} out of range [0, 99]")
        }
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
        } else if (text ==~ /(\d{3,10})/){
            def val1    = ( text =~ /(\d{3,10})/)[0][1]

            throw new IntegerMatcherException("Number too big: ${val1}")
        } else {
            throw new IntegerMatcherException("Unmatched text \"${text}\"")
        }

        if (result.findAll{ it < min || max < it}){
            throw new IntegerMatcherException("Value out of range: ${text}. Range [${min}, ${max}]")
        }
        return result
    }
}
