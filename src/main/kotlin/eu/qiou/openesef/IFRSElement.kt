package eu.qiou.openesef

data class IFRSElement(val id:String, val name:String, val nillable:Boolean,
                       val type: IFRSElementType, val isDimensionItem:Boolean = false,
                       val isDuration: Boolean = false, val isAbstract:Boolean = false, val isDebit: Boolean = false){
    fun toList():List<String>{
        return listOf(id, name, type.name)
    }
}