package eu.qiou.openesef

import org.xml.sax.ContentHandler
import org.xml.sax.InputSource
import javax.xml.parsers.SAXParserFactory

class XMLParser {
    fun parse(file: String, handler: ContentHandler){
       SAXParserFactory.newInstance().newSAXParser().xmlReader.apply {
           contentHandler = handler
       }.parse(InputSource(file))
    }
}