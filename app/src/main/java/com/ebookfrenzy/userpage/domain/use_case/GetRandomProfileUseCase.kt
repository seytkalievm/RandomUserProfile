package com.ebookfrenzy.userpage.domain.use_case

import com.ebookfrenzy.userpage.common.Resource
import com.ebookfrenzy.userpage.domain.models.Profile
import com.ebookfrenzy.userpage.domain.repositories.RandomProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetRandomProfileUseCase @Inject constructor(
    private val repo: RandomProfileRepository,
) {

    operator fun invoke(): Flow<Resource<Profile>> =  flow{
        try{
            emit(Resource.Loading())
            emit(Resource.Success(repo.getRandomProfile()))
        } catch (e: HttpException){
            emit(Resource.Error(e.toString()))
        } catch (e: IOException){
            emit(Resource.Error(e.toString()))
        }

    }
}