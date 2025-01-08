

import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClothesUseCase @Inject constructor(
    private val repository: ClothesRepository
) {

    operator fun invoke(): Flow<Resource<List<Clothes>>> = flow {
        try {
            emit(Resource.Loading())
            val clothes = repository.getClothes()
            emit(Resource.Success(clothes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
