package eu.qiou.openesef.util

import eu.qiou.openesef.IFRSElementType
import org.jsoup.Jsoup

object MandatoryMarkups {
    fun fetch(url:String, selector:String = "#L_2022339EN\\.01000601 > div > div > div > div > div:nth-child(4) > table > tbody > tr"):List<MandatoryMarkup>{
        return Jsoup.connect(url).get().select(selector).drop(1).map {
            val tmp = it.select("td").map { it.wholeText().trim() }
            MandatoryMarkup(tmp[0],when{
                tmp[1] == "text" -> IFRSElementType.STRING
                tmp[1] == "text block"-> IFRSElementType.TEXT_BLOCK
                tmp[1].startsWith("X.XX") -> IFRSElementType.DECIMAL
                tmp[1].startsWith("X") -> IFRSElementType.MONETARY
                else -> IFRSElementType.TEXT_BLOCK
            }, tmp[2].split(",").map { it.trim() })
        }
    }
}