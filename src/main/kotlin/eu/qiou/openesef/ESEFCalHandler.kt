package eu.qiou.openesef

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

/**
 *  calculationLink
 *
 *  two types of element of Calculation
 *
 *  - link:loc    with  xlink:label   as variable name for the components
 *
 *  - link:calculationArc
 *  xlink:from   item to be calculated
 *  xlink:to     components employed in the calculation
 *  order       position order of element
 *  weight      multiplier of the component
 */


class ESEFCalHandler(val roleType: Map<String, RoleType>, val element: Map<String, IFRSElement>): DefaultHandler() {

    private lateinit var res: RoleType
    private val map =  mutableMapOf<String, IFRSElement>()
    private lateinit var cals: MutableMap<String, Calculation>


    @Override
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        when(qName!!){
            "link:calculationLink", "calculationLink" -> {
                val id = attributes!!.getValue("xlink:role").split("/").last()

                res = roleType[id] ?: throw Exception("Unknown id in the definition: $id")
                cals = mutableMapOf()
                map.clear()
            }
            "link:loc", "loc" -> {
                val id = attributes!!.getValue("xlink:href").split("#").last()
                // xlink:label
                val label = attributes.getValue("xlink:label")
                map[label] = element[id] ?: throw Exception("Unknown id in the definition: $id")
            }
            "calculationArc", "link:calculationArc" -> {
                val order = attributes!!.getValue("order").toDouble()
                val weight = attributes.getValue("weight").toDouble()
                val operation = attributes.getValue("xlink:arcrole").split("/").last()

                if (operation != "summation-item") throw Exception("Unknown operation: $operation")

                val lFrom = attributes.getValue("xlink:from")
                val lTo = attributes.getValue("xlink:to")

                if (!cals.containsKey(lFrom)){
                    cals[lFrom] = Calculation(lFrom)
                }else{
                    cals[lFrom]!!.list.add(CalculationArc(order,weight,lFrom,lTo))
                }
            }
        }
    }
}