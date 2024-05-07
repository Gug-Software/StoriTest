package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.movement.BankMovementsRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetBankMovementsRemoteUseCase {
    suspend operator fun invoke(
    ): Flow<NetworkResult<Any?>>
}

class GetBankMovementsRemoteUseCaseImpl(
    private val bankMovementsRepository: BankMovementsRepository
) : GetBankMovementsRemoteUseCase {
    override suspend operator fun invoke(
    ): Flow<NetworkResult<Any?>> {
        return bankMovementsRepository.getMovementsData()
    }
}