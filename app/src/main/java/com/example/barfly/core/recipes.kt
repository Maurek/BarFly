package com.example.barfly.core

enum class BarRecipeType(val type:Int)
{
    Cocktail(0), Mocktail(1), Infusion(2), Syrup(3), Bitter(4), Punch(5)
}
//TODO need to move BarIngredientName into a struct that also has quantity and quantity type (ml,twist,wedges,...)
//TODO create unit tests for getRecipesInStock()
data class BarRecipeInfo(val type:BarRecipeType,
                         val name:String,
                         val glass:String,
                         val source:String,
                         val ingredients:List<BarIngredientName>)
{
}

object BarRecipes
{
    val recipes = mutableListOf<BarRecipeInfo>()
    fun getRecipesInStock() : List<BarRecipeInfo> {
        var result = mutableListOf<BarRecipeInfo>()
        for( r in recipes) {
            var gotAllIngredients = true
            for( i in r.ingredients) {
                val hasIngredient : Pair<Boolean, Boolean> = BarIngredients.hasIngredient( i )
                if(!hasIngredient.first && !hasIngredient.second)
                {
                    gotAllIngredients = false
                    break
                }
            }
            if( gotAllIngredients) {
                result.add( r )
            }
        }
        return result
    }
}