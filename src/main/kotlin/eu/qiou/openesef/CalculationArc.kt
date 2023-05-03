package eu.qiou.openesef

data class CalculationArc(val order: Double, val weight: Double, val fromRef: String, val toRef: String){
    override fun toString(): String {
        return "+ $toRef${if (weight == 1.0) "" else " * $weight"}"
    }
}