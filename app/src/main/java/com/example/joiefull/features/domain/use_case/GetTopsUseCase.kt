package com.example.joiefull.features.domain.use_case

import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import java.io.IOException
import retrofit2.HttpException


class GetTopsUseCase (private val repository: ClothesRepository) : KoinComponent {

    operator fun invoke(): Flow<Resource<List<Clothes>>> = flow {
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
