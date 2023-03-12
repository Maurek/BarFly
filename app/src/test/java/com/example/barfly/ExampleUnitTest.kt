package com.example.barfly

import com.example.barfly.core.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BarFlyUnitTest {
    @Test
    fun ingredientsAddedCorrectly() {
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Bitters, BarIngredientName("Bitters.Aromatic.Angostura"), true))

        var hasIngredient : Pair<Boolean, Boolean> = BarIngredients.hasIngredient( BarIngredientName( "Bitters.Aromatic.Angostura") )
        assertEquals( "Checking Ango", hasIngredient.first, true)
        assertEquals( "Checking Ango", hasIngredient.second, false)

        hasIngredient = BarIngredients.hasIngredient( BarIngredientName( "Bitters.Aromatic.Peychauds") )
        assertEquals( "Checking Peychaud", hasIngredient.first, false)
        assertEquals( "Checking Peychaud", hasIngredient.second, true)

        hasIngredient = BarIngredients.hasIngredient( BarIngredientName( "Bitters.Aromatic") )
        assertEquals( "Checking Aromatic", hasIngredient.first, true)
        assertEquals( "Checking Aromatic", hasIngredient.second, false)

        hasIngredient = BarIngredients.hasIngredient( BarIngredientName( "Gin.Floral") )
        assertEquals( "Checking Gin", hasIngredient.first, false)
        assertEquals( "Checking Gin", hasIngredient.second, false)
    }

    @Test
    fun recipesAndIngredients()
    {
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Bitters, BarIngredientName("Bitters.Aromatic.Angostura"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Alcohol, BarIngredientName("Gin.Floral"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Alcohol, BarIngredientName("Rum.Type2"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Syrup, BarIngredientName("Syrup.Simple"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Mixer, BarIngredientName("Tonic.Indian"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Mixer, BarIngredientName("Tonic.Cucumber"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Ice, BarIngredientName("Ice.Dirty"), true))
        BarIngredients.ingredients.add(BarIngredientInfo( BarIngredientType.Ice, BarIngredientName("Ice.Clear"), false))

        var recipeGT : BarRecipeInfo = BarRecipeInfo(BarRecipeType.Cocktail, "G&T", "Collins", "Me", listOf(BarIngredientName("Gin.Citrus"), BarIngredientName("Tonic"), BarIngredientName("Ice.Clear")))
        var recipeMaiTai : BarRecipeInfo = BarRecipeInfo(BarRecipeType.Cocktail, "Mai Tai", "Tocks glass", "Me", listOf(BarIngredientName("Rum.Type1"), BarIngredientName("Orgeat"), BarIngredientName("Lime")))
        var recipeOldFashioned : BarRecipeInfo = BarRecipeInfo( BarRecipeType.Cocktail, "Old fashioned", "Rocks", "Me", listOf(BarIngredientName("Rum.Type1"), BarIngredientName("Syrup.Rich"), BarIngredientName("Bitters.Aromatic.Angostura")))
        BarRecipes.recipes.add( recipeGT )
        BarRecipes.recipes.add( recipeOldFashioned )
        BarRecipes.recipes.add( recipeMaiTai )
        var recipes = BarRecipes.getRecipesInStock()
        var expectedRecipies = mutableListOf<BarRecipeInfo>()
        expectedRecipies.add( recipeGT )
        expectedRecipies.add( recipeOldFashioned )

        assertEquals( "Checking recipes", recipes, expectedRecipies)
    }
}