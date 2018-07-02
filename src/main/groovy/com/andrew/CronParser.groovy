package com.andrew

class CronParser {
    def parserConfig = []

    static void main(String[] args) {
        println args
    }
    CronParser() {
        this.parserConfig = [
            new IntegerMatcher('minute', 0, 59),
            new IntegerMatcher('hour', 0, 23),
            new IntegerMatcher('day of month', 1, 31),
            new IntegerMatcher('month', 1, 12),
            new IntegerMatcher('day of week', 1, 7)
        ]
    }

    def parseExpression(cronString){

        def result  = new CronExpression()

//      Split cron String into its space separated parts
        def parts   = cronString.split(/ /)

        parserConfig.eachWithIndex{partMatcher, index ->
//            List of numeric values returned from parsing the
            def vals    = []

//            Split comma separated part
            parts[index].split(/,/).each{
                vals    += partMatcher.parseText(it)
            }
            result.parts    += [ name : partMatcher.name, values : vals.unique().sort() ]
        }
    }
}
