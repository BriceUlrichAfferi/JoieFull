

import com.example.joiefull.common.Resource
import com.example.joiefull.features.domain.model.Clothes
import com.example.joiefull.features.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class GetClothesUseCase(private val repository: ClothesRepository) : KoinComponent {

    operator fun invoke(clotheId: Int): Flow<Resource<Clothes>> = flow {
        try {
            emit(Resource.Loading())
            val clothes = repository.getClothesById(clotheId)
            emit(Resource.Success(clothes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
