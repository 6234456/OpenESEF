package eu.qiou.openesef.util

import eu.qiou.openesef.IFRSElementType

data class MandatoryMarkup(val label:String, val type: IFRSElementType, val references: List<String>)