package com.andrew

import com.andrew.CronExpression
import com.andrew.CronParserException
import com.andrew.IntegerMatcherException

class CronParser {
    def parserConfig = []

    static void main(String[] args) {
        def parser  = new CronParser()

        try {
            if (args.size() == 1 && args[0] ==~ /help|-h|-\?/){
                println "Usage:\t${parser}"
                System.exit(0)
            }

            def result  = parser.parseArgs(args)

            println result
        } catch (IntegerMatcherException e) {
            println "Error:\t${e.message}"
            println "Args:\t" + args.join(' ')

            println "Usage:\t${parser}"

            System.exit(1)
        } catch (Exception e){
            println "Error:\t${e.message}"
            println "Args:\t" + args.join(' ')
            println "Usage:\t${parser}"

            System.exit(1)
        }
    }
    // Default config, if none provided via the 2nd constructor
    CronParser() {
        this.parserConfig = [
            new IntegerMatcher('minute', 0, 59),
            new IntegerMatcher('hour', 0, 23),
            new IntegerMatcher('day of month', 1, 31),
            new IntegerMatcher('month', 1, 12),
            new IntegerMatcher('day of week', 1, 7)
        ]
    }
    // specify the config. Useful for testing
    CronParser(parserConfig){
        this.parserConfig   = parserConfig
    }

    def parseArgs(args){
        def result  = new CronExpression()

        parserConfig.eachWithIndex{partMatcher, index ->
            def vals    = []

            args[index].split(',').each{
                vals    += partMatcher.parseText(it)
            }
//            result.parts    += [ name : partMatcher.name, values : vals.unique().sort() ]
            result.addPart(partMatcher.name, vals.unique().sort())
        }

        if (args.size() > parserConfig.size()){
//            println args[ parserConfig.size()  .. -1 ]
            result.parts    += [ name : 'command', values : args[ parserConfig.size()  .. -1 ] ]
        } else {
            throw new CronParserException('No command found')
        }
        return result
    }

    @java.lang.Override
    public java.lang.String toString() {
        def result  = this.parserConfig.collect{ "${it.min}-${it.max}"  }.join(' ') + ' Command String'

        return result
    }

}
