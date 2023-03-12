package com.example.barfly.core

enum class BarIngredientType(val type:Int)
{
    Alcohol(0), Bitters(1), Ice(2), Syrup(3), Plant(4), Mixer(5)
}
//TODO Create unit tests for doesIngredientMatch()
//TODO Create unit tests for getIngredientsInStock()
//TODO Create unit tests for hasIngredient
class BarIngredientName(val name:String)
{
    fun doesIngredientMatch( other:BarIngredientName ) : Pair<Boolean, Boolean>
    {
        //Gin.Floral against Gin.Floral should return (true,false)
        if( other.name == name) return true to false
        //Gin against Gin.Floral should return (true, false)
        if( name.contains( other.name )) return true to false
        //Gin.Floral.Swedish against Gin.Floral should return (false, true)
        if( other.name.contains( name )) return false to true

        //Now we start going up the hierarchy of other
        var tmpOther = other.getParent()
        while (tmpOther.isValid())
        {
            //Gin against Gin.Floral should return (true, false)
            if( name.contains( tmpOther.name )) return false to true
            //Gin.Floral.Swedish against Gin.Floral should return (false, true)
            if( tmpOther.name.contains( name )) return false to true
            tmpOther = tmpOther.getParent()
        }

        return false to false
    }

    fun getParent() :BarIngredientName
    {
        if( name.contains('.') ) {
            return BarIngredientName(name.substringBeforeLast('.'))
        }
        return BarIngredientName("")
    }
    fun isValid(): Boolean = name.isNotEmpty()
}
data class BarIngredientInfo(val type:BarIngredientType,
                             val name:BarIngredientName,
                             val inStock:Boolean)
{
}

object BarIngredients
{
    var ingredients = mutableListOf<BarIngredientInfo>()
    fun getIngredientsInStock() : List<BarIngredientInfo> {
        val result = mutableListOf<BarIngredientInfo>()
        for( i in ingredients)
        {
            if( i.inStock )
            {
                result.add( i )
            }
        }
        return result
    }
    fun hasIngredient( ingredient : BarIngredientName) : Pair<Boolean, Boolean>{
        var inStock : Boolean
        var subInStock : Boolean
        inStock = false
        subInStock = false
        for( i in ingredients) {
            if( i.inStock )
            {
                val result = i.name.doesIngredientMatch( ingredient )
                if( result.first )
                    inStock = true
                if(result.second)
                    subInStock = true
            }
        }
        return inStock to subInStock
    }
}