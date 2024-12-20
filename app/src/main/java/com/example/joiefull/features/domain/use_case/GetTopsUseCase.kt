package com.example.joiefull.features.domain.use_case

import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException


class GetTopsUseCase @Inject constructor(
    private val repository: ClothesRepository
) {

    operator fun invoke(topsCategory: String): Flow<Resource<List<Clothes>>> = flow {
        try {
            emit(Resource.Loading())
            val clothes = repository.getClothesByCategory("TOPS")
            emit(Resource.Success(clothes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
