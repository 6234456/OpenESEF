package eu.qiou.openesef

import eu.qiou.aaf4k.util.io.ExcelUtil
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

        ExcelUtil.writeData("demo.xlsx", data = all.roles.map { it.toList().let { x-> x.first() to x } }.toMap())
    }

    //
    @Test
    fun parseIFRS() {
        val ifrs = IFRSElementHandler()
        XMLParser().parse("src/main/resources/esef_taxonomy_2021/www.esma.europa.eu/taxonomy/ext/full_ifrs-cor_2021-03-24.xsd", ifrs)

        ExcelUtil.writeData("demoIFRS.xlsx", data = ifrs.elements.map { it.toList().let { x-> x.first() to x } }.toMap())


    }
}