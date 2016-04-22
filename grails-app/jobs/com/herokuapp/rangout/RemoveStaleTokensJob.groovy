package com.herokuapp.rangout

import groovy.time.TimeCategory

class RemoveStaleTokensJob {
    static triggers = {
        cron name: 'every30seconds', startDelay:10000, cronExpression: "0/30 * * * * ?"
    }

    def execute() {
        log.debug "Searching for expired tokens..."

        def c = AuthenticationToken.createCriteria()
        def results = c {
            or {
                def fourteenDays = use(TimeCategory) {
                    new Date() - 14.hours
                }
                eq('expired', true)
                lt('refreshed', fourteenDays)
               }
        }
        results?.each { token ->
            log.debug "Token ${token.tokenValue} expired, deleting..."

            AuthenticationToken.withTransaction() {
                token.delete(flush: true)
            }
        }
    }
}
