package com.andrew

class CronExpression {
    def parts   = []


    @java.lang.Override
    public java.lang.String toString() {
        return parts.each { "${it.name}\t " + it.values.join(' ')}.join(/\n/)
    }
}
