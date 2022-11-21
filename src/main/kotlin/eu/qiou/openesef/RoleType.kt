package eu.qiou.openesef

data class RoleType(val id: String, val definition: String,
                    val isPresentation: Boolean, val isCalculation: Boolean, val isDefinition: Boolean,
                    val isValidation: Boolean){


    fun toList():List<String>{
        return listOf(id.split("-").last(), id, definition)
    }
}