package eu.qiou.openesef

enum class IFRSElementType {
    STRING,
    DOMAIN,
    MONETARY,
    PERCENT,
    DURATION,
    DECIMAL,
    TEXT_BLOCK,
    SHARES,
    AREA,
    PER_SHARE,
    DATE,

    // This datatype serves as the type for dimensionless numbers such as percentage change, growth rates,
    // and other ratios where the numerator and denominator have the same units.
    PURE,
}