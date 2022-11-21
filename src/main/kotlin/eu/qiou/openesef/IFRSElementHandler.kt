package eu.qiou.openesef

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class IFRSElementHandler: DefaultHandler() {
    val elements = mutableListOf<IFRSElement>()

    @Override
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        when(qName!!){
            "xsd:element" -> {
                elements.add(IFRSElement(
                        id = attributes!!.getValue("id"),
                        name = attributes.getValue("name"),
                        nillable = attributes.getValue("nillable") == "true",
                        isDimensionItem = attributes.getValue("substitutionGroup") == "xbrldt:dimensionItem",
                        type = when(attributes.getValue("type")){
                            "xbrli:monetaryItemType" -> IFRSElementType.MONETARY
                            "xbrli:stringItemType" -> IFRSElementType.STRING
                            "nonnum:domainItemType" -> IFRSElementType.DOMAIN
                            "num:percentItemType" -> IFRSElementType.PERCENT
                            "xbrli:durationItemType" -> IFRSElementType.DURATION
                            "nonnum:textBlockItemType" -> IFRSElementType.TEXT_BLOCK
                            "xbrli:decimalItemType" -> IFRSElementType.DECIMAL
                            "xbrli:sharesItemType" -> IFRSElementType.SHARES
                            "num:areaItemType" -> IFRSElementType.AREA
                            "num:perShareItemType" -> IFRSElementType.PER_SHARE
                            "xbrli:dateItemType" -> IFRSElementType.DATE
                            "xbrli:pureItemType" -> IFRSElementType.PURE
                            else -> throw Exception("Unknown ElementType: ${attributes.getValue("type")}")
                        },
                        isDuration = attributes.getValue("xbrli:periodType") == "duration",
                        isDebit = attributes.getValue("xbrli:balance") == "debit"
                    )
                )
            }
        }
    }


}