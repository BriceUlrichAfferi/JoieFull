import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClotheUseCase @Inject constructor(
    private val repository: ClothesRepository
) {

    operator fun invoke(clotheId: Int): Flow<Resource<Clothes>> = flow {
        try {
            emit(Resource.Loading())
            val clothe = repository.getClothesById(clotheId)
            emit(Resource.Success(clothe))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
