package com.example.mypet.data.remote_data_source.model.parameters

data class ParametersResponse(
    val id: Int,
    val clientId: Int,
    val parametersArray: List<Parameters>
)
