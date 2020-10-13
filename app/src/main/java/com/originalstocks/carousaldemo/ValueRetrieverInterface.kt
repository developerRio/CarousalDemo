package com.originalstocks.carousaldemo

interface ValueRetrieverInterface {
    fun onClickValueRetriever(position: Int, itemName: String)

    fun onSpecificValueRetriever(position: Int, itemName: String)
}