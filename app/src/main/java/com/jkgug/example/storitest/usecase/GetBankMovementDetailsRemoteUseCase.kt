package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.movement.BankMovementDetailsRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetBankMovementDetailsRemoteUseCase {
    suspend operator fun invoke(
        movementId: String
    ): Flow<NetworkResult<Any?>>
}

class GetBankMovementDetailsRemoteUseCaseImpl(
    private val bankMovementDetailsRepository: BankMovementDetailsRepository
) : GetBankMovementDetailsRemoteUseCase {
    override suspend operator fun invoke(
        movementId: String
    ): Flow<NetworkResult<Any?>> {
        return bankMovementDetailsRepository.getMovementDetails(movementId)
    }
}