package eu.qiou.openesef

import eu.qiou.aaf4k.util.io.ExcelUtil
import eu.qiou.openesef.util.CoreTaxonomyHint
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class XMLParserTest {

    @Test
    fun parseTest() {
        val all = ESEFAllHandler()
        XMLParser().parse(
            "src/main/resources/esef_taxonomy_2021/www.esma.europa.eu/taxonomy/2021-03-24/esef_all.xsd",
            all
            )

        println(all.roles[15])
        val ifrs = IFRSElementHandler()
        XMLParser().parse("src/main/resources/esef_taxonomy_2021/www.esma.europa.eu/taxonomy/ext/full_ifrs-cor_2021-03-24.xsd", ifrs)

        val calHandler = ESEFCalHandler(all.roles.associateBy { it.id }, ifrs.elements.associateBy { it.id })
        XMLParser().parse("src/main/resources/esef_taxonomy_2021/www.esma.europa.eu/taxonomy/2021-03-24/esef_all-cal.xml", calHandler)
        println(calHandler.calculations)


        // ExcelUtil.writeData("demoIFRS.xlsx", data = ifrs.elements.associate { it.toList().let { x -> x.first() to x } })
    }

    //
    @Test
    fun parseIFRS() {
        val ifrs = IFRSElementHandler()
        XMLParser().parse("src/main/resources/esef_taxonomy_2021/www.esma.europa.eu/taxonomy/ext/full_ifrs-cor_2021-03-24.xsd", ifrs)

        // ExcelUtil.writeData("demoIFRS.xlsx", data = ifrs.elements.associate { it.toList().let { x -> x.first() to x } })
        //println((CoreTaxonomyHint.fetch("https://eur-lex.europa.eu/legal-content/EN/TXT/HTML/?uri=OJ:L:2022:339:FULL").keys).minus(ifrs.elements.map { it.name }.toSet()))
        println((CoreTaxonomyHint.fetch("https://eur-lex.europa.eu/legal-content/EN/TXT/HTML/?uri=OJ:L:2022:339:FULL")))
    }
}