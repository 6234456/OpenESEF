package eu.qiou.openesef

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

class ESEFAllHandler: DefaultHandler() {

    val roles = mutableListOf<RoleType>()
    private var data = StringBuilder()
    private var id = ""
    private var definition = ""
    private var isPresentation = false
    private var isDefinition = false
    private var isCal = false
    private var isValidate = false



    @Override
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        when(qName!!){
            "link:roleType" -> id = attributes!!.getValue("id")
        }

        data = StringBuilder()
    }

    @Override
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when(qName!!){
            "link:definition" -> definition = data.toString()
            "link:usedOn" -> {
                when(data.toString()){
                    "link:presentationLink" -> isPresentation = true
                    "link:definitionLink" -> isDefinition = true
                    "link:calculationLink" -> isCal = true
                    "gen:link" -> isValidate = true
                }
            }
            "link:roleType" -> {
                roles.add(RoleType(id,definition, isPresentation, isCal, isDefinition, isValidate))

                data = StringBuilder()
                id = ""
                definition = ""
                isPresentation = false
                isDefinition = false
                isCal = false
                isValidate = false
            }
        }
    }

    @Override
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        data.append(String(ch!!, start, length))
    }
}