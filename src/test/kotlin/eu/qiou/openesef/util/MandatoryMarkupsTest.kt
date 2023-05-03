package eu.qiou.openesef.util

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class MandatoryMarkupsTest{
    @Test
    fun trailFetch(){
        println(MandatoryMarkups.fetch("https://eur-lex.europa.eu/legal-content/EN/TXT/HTML/?uri=OJ:L:2022:339:FULL"))
    }

    @Test
    fun trailFetch0(){
        println(MandatoryMarkups.fetch("https://eur-lex.europa.eu/legal-content/EN/TXT/HTML/?uri=OJ:L:2022:339:FULL"))
    }
}