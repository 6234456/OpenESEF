package eu.qiou.openesef


/**
 *
 * table -> axis -> member
 *
 *
 * table:   denotes the beginning of a structure represented by a table where rows and columns contribute to definition of a financial concept on their intersection
 * axis:    denotes a dimensional property in a tabular structure
 * member:  denotes a member of a dimension on an axis
 *
 */
data class IFRSElement(val id:String, val name:String, val nillable:Boolean,
                       val type: IFRSElementType, val isDimensionItem:Boolean = false,
                       val isDuration: Boolean = false, val isAbstract:Boolean = false, val isDebit: Boolean = false){
    fun toList():List<String>{
        return listOf(id, name, type.name)
    }
}