package eu.qiou.openesef.util

import eu.qiou.openesef.IFRSElementType
import org.jsoup.Jsoup

object CoreTaxonomyHint {

    fun fetch(url:String, selector:String = "#L_2022339EN\\.01001701 > div > div > div > div > table > tbody > tr", totalCol: Int = 6):Map<String, CoreTaxonomyHintParam>{
        var key:String? = null
        var documentation: String? = null
        var totalLabel: String? = null
        var label: String? = null
        var negatedLabel: String? = null
        var commentaryGuidance: String? = null
        var periodStartLabel:String? = null
        var periodEndLabel:String? = null
        var reference:String? = null

        fun process(pLabel:String, content:String){
            when(pLabel){
                "label" -> label = content
                "documentation" -> documentation = content
                "totalLabel" -> totalLabel = content
                "negatedLabel" -> negatedLabel = content
                "commentaryGuidance" -> commentaryGuidance = content
                "periodStartLabel" -> periodStartLabel = content
                "periodEndLabel" -> periodEndLabel = content
            }
        }
        val res: MutableMap<String, CoreTaxonomyHintParam> = mutableMapOf()
        Jsoup.connect(url).apply { maxBodySize(0) }.get().select(selector).drop(1).forEach {
            val cols = it.select("td").map { it.wholeText().trim() }
            if (cols.size == totalCol){
                if (key != null){
                  res[key!!] = CoreTaxonomyHintParam(label = label!!, documentation = documentation,
                      reference = reference!!, commentaryGuidance = commentaryGuidance, totalLabel = totalLabel,
                      negatedLabel = negatedLabel, periodEndLabel = periodEndLabel, periodStartLabel = periodStartLabel)

                    label = null
                    documentation = null
                    totalLabel = null
                    negatedLabel = null
                    commentaryGuidance = null
                    periodStartLabel = null
                    periodEndLabel = null
                }

                key = cols[1]
                reference = cols[totalCol - 1]

                process(cols[3], cols[4])
            }else{
                process(cols[0], cols[1])
            }
        }

        res[key!!] = CoreTaxonomyHintParam(label = label!!, documentation = documentation,
            reference = reference!!, commentaryGuidance = commentaryGuidance, totalLabel = totalLabel,
            negatedLabel = negatedLabel, periodEndLabel = periodEndLabel, periodStartLabel = periodStartLabel)

        return res
    }

}