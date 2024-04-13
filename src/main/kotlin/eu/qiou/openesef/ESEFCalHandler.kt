package eu.qiou.openesef

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

/**
 *  calculationLink
 *  description:  the link:calculationLink element is used to define face of financial statements and tables
 *
 *  two types of element of Calculation
 *
 *  - link:loc    with  xlink:label   as variable name for the components
 *
 *  - link:calculationArc
 *  xlink:from   item to be calculated  ->  the supper class of xlink:to
 *  xlink:to     components employed in the calculation ->  the children of xlink:from
 *  order       position order of element
 *  weight      multiplier of the component
 *
 *  roleType
 *  - id    unique identifier of the role type == disclosure type in the ESEF like BS PL
 *  - name of the role type
 */


class ESEFCalHandler(val roleType: Map<String, RoleType>, val element: Map<String, IFRSElement>): DefaultHandler() {

    private lateinit var role: RoleType
    private val map =  mutableMapOf<String, IFRSElement>()
    private lateinit var cals: MutableList<CalculationArc>

    val calculations:MutableMap<RoleType, Map<IFRSElement, Calculation>>  = mutableMapOf()

    @Override
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when(qName!!){
            "link:calculationLink", "calculationLink" -> {
                calculations[role] =
                    cals.groupBy { it.fromRef }.map { map[it.key]!! to  Calculation(it.value.map { x->
                        IFRSCalculationComponent( map[x.toRef]!!, x.weight, x.order)
                    }) }
                        .toMap().toMutableMap()
            }
        }
    }

    @Override
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        when(qName!!){
            "link:calculationLink", "calculationLink" -> {
                val id = attributes!!.getValue("xlink:role").split("/").last()

                role = roleType[id] ?: throw Exception("Unknown id in the definition: $id")
                cals = mutableListOf()
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

                cals.add(CalculationArc(order,weight,lFrom,lTo))
            }
        }
    }
}